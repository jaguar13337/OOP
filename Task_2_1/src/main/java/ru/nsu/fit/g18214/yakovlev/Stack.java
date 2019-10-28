package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

  private Element<T> head;
  private Element<T> currElem;
  private int count;

  /** Generates empty stack. */
  public Stack() {
    this.head = new Element<T>(null);
    this.currElem = this.head;
    this.count = 0;
  }

  /**
   * Pushing current object into the stack.
   *
   * @param object which object you want to push into the stack
   */
  public void push(T object) {
    Element<T> n = new Element<T>(object);
    n.setPrev(currElem);
    currElem.setNext(n);
    currElem = n;
    count++;
  }

  /**
   * Get last pushed value
   *
   * @return Last object, which was pushed
   * @throws StackException If there's no object to pop - throws exception, that it is empty
   */
  public T pop() throws StackException {
    if (currElem == head) throw new StackException("Stack is empty");
    else {
      T val = currElem.getVal();
      currElem = currElem.getPrev();
      count--;
      return val;
    }
  }

  /**
   * return count of elements in the stack
   *
   * @return count of elements
   */
  public int count() {
    return count;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      int cnt = count();
      Element<T> elem = currElem;

      @Override
      public boolean hasNext() {
        if (cnt > 0) return true;
        return false;
      }

      @Override
      public T next() {
        T tmp = elem.getVal();
        cnt--;
        elem = elem.getPrev();
        return tmp;
      }
    };
  }
}
