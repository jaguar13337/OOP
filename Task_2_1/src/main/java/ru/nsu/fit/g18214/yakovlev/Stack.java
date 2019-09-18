package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;

public class Stack implements Iterable<Object> {
    private Object[] stack;
    private int currElem;

    /**
     * Generates stack with capacity = 100. If you don't know size of your stack
     */
    public Stack() {
        this.stack = new Object[100];
        this.currElem = 0;
    }


    /**
     * Generates stack with given capacity. If you exactly know, which capacity you want
     * @param capacity started capacity
     */
    public Stack(int capacity) {
        this.stack = new Object[capacity];
        this.currElem = 0;
    }

    /**
     * Pushing current object into the stack.
     * @param object which object you want to push into the stack
     */
    public void push(Object object) {
        if (currElem == stack.length) {
            Object[] newStack = new Object[stack.length * 2];
            System.arraycopy(stack,0,newStack,0,stack.length);
            stack = newStack;
        }
        stack[currElem++] = object;
    }

    /**
     * Get last pushed value
     * @return Last object, which was pushed
     * @throws StackException If there's no object to pop - throws exception, that it is empty
     */
    public Object pop() throws StackException {
        if (currElem == 0)
            throw new StackException("Stack is empty");
        else {
            currElem--;
            return stack[currElem];
        }
    }

    /**
     * Returns count of elements on stack
     * @return count of elements
     */
    public int count() {
        return currElem;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            @Override
            public boolean hasNext() {
                if (count() > 0)
                    return true;
                return false;
            }

            @Override
            public Object next() {
                return pop();
            }
        };
    }
}
