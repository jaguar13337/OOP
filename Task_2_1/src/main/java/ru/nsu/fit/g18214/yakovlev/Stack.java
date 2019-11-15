package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.NullArgumentException;

public class Stack<T> implements Iterable<T> {

  private Element<T> currElem;
  private int count;

  /** Generates empty stack. */
  public Stack() {
    this.currElem = new Element<T>(null);
    this.count = 0;
  }

  /**
   * Pushing current object into the stack.
   *
   * @param object which object you want to push into the stack
   * object mustn't be null
   */
  public void push(T object) {
    if (object == null)
      throw new NullArgumentException();
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
    if (count == 0) throw new StackException("Stack is empty");
    else {
      T val = currElem.getVal();
      currElem = currElem.getPrev();
      currElem.setNext(null);
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
      Element<T> elem = currElem;

      @Override
      public boolean hasNext() {
        return elem.getVal() != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T tmp = elem.getVal();
        elem = elem.getPrev();
        return tmp;
      }
    };
  }
}
