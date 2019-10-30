package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class OrderedQueue<K extends Comparable<K>, T extends Comparable<T>> implements Iterable<T> {

  private Element<K, T> head;
  private int cnt;

  /** Generates new empty queue */
  public OrderedQueue() {
    this.head = new Element<>(null, null);
    this.cnt = 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private int counter = cnt;
      private int askcount = 0;
      private List<Element<K, T>> alreadyFinded = new ArrayList<>();

      @Override
      public boolean hasNext() {
        return (counter != 0);
      }

      @Override
      public T next() {
        if (askcount + counter != cnt)
          throw new ConcurrentModificationException("You break the queue");
        Element<K, T> tmp = head.getNext();
        Element<K, T> maximal = null;
        if (!alreadyFinded.contains(tmp))
            maximal = tmp;
        while (tmp != null) {
          if (tmp.compareTo(maximal) > 0 && !alreadyFinded.contains(tmp)) {
            maximal = tmp;
          }
          tmp = tmp.getNext();
        }
        alreadyFinded.add(maximal);
        counter--;
        askcount++;
        return maximal.getValue();
      }
    };
  }

  /**
   * Add an element to the queue
   *
   * @param key which is determine place of element
   * @param value element value
   */
  public void insert(K key, T value) {
    Element<K, T> element = new Element<>(key, value);
    Element<K, T> tmp = head;
    while (tmp.getNext() != null) tmp = tmp.getNext();
    element.setPrev(tmp);
    tmp.setNext(element);
    cnt++;
  }

  /**
   * returns element with highest key
   *
   * @return value of element
   */
  public T extractMax() {
    if (head.getNext() == null) return null;
    Element<K, T> tmp = head.getNext();
    Element<K, T> maximal = tmp;
    while (tmp != null) {
      if (maximal.compareTo(tmp) < 0) maximal = tmp;
      tmp = tmp.getNext();
    }
    maximal.getPrev().setNext(maximal.getNext());
    if (maximal.getNext() != null) maximal.getNext().setPrev(maximal.getPrev());
    cnt--;
    return maximal.getValue();
  }
}
