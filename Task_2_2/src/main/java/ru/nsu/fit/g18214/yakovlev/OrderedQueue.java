package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedQueue<K extends Comparable<K>, T> implements Iterable<T> {

  private Element<K, T> head;
  private int cnt;

  /**
   * Generates new empty queue
   */
  public OrderedQueue() {
    this.head = null;
    this.cnt = 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private int counter = 0;

      @Override
      public boolean hasNext() {
        return (cnt - counter != 0);
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        Element<K, T> elem = head;
        for (int i = 0; i < counter; i++) {
          elem = elem.getNext();
        }
        counter++;
        return elem.getValue();
      }
    };
  }

  /**
   * Add an element to the queue
   *
   * @param key which is determine place of element. MUSTN'T BE NULL!!
   * @param value element value
   * @throws IllegalArgumentException when you put null as a key.
   */
  public void insert(K key, T value) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    Element<K, T> element = new Element<>(key, value);
    cnt++;
    if (element.compareTo(head) >= 0) {
      element.setNext(head);
      head = element;
      return;
    }
    Element<K, T> tmp = head;
    for (int i = 0; i < cnt - 1; i++) {
      if (element.compareTo(tmp.getNext()) >= 0) {
        element.setNext(tmp.getNext());
        tmp.setNext(element);
      }
      tmp = tmp.getNext();
    }
  }

  /**
   * returns element with a highest key
   *
   * @return value of element or null, if queue is empty.
   */
  public T extractMax() {
    if (head == null) {
      return null;
    }
    cnt--;
    Element<K, T> elem = head;
    head = head.getNext();
    return elem.getValue();
  }
}
