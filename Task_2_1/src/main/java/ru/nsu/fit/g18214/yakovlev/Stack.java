package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;

public class Stack<T>  implements Iterable<T> {

    private Element<T> head;
    private int currElem;

    /**
     * Generates empty stack.
     */
    public Stack() {
        this.head  = new Element<T>(null);
        this.currElem = 0;
    }

    /**
     * Pushing current object into the stack.
     * @param object which object you want to push into the stack
     */
    public void push(T object) {
        Element<T> n = new Element<T>(object);
        Element<T> tmp = head;
        while(tmp.getNext() != null)
            tmp = tmp.getNext();
        tmp.setNext(n);
        currElem++;
    }

    /**
     * Get last pushed value
     * @return Last object, which was pushed
     * @throws StackException If there's no object to pop - throws exception, that it is empty
     */
    public T pop() throws StackException {
        if (currElem == 0)
            throw new StackException("Stack is empty");
        else {
            currElem--;
            Element<T> tmp = head;
            while (tmp.getNext().getNext() != null)
                tmp = tmp.getNext();
            Element<T> ret = tmp.getNext();
            tmp.setNext(null);
            return ret.getVal();
        }
    }

    /**
     * return count of elements in the stack
     * @return count of elements
     */
    public int count() {
        return currElem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (count() > 0)
                    return true;
                return false;
            }

            @Override
            public T next() {
                return pop();
            }
        };
    }
}
