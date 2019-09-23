package ru.nsu.fit.g18214.yakovlev;

public class Element<T> {

    private T val;
    private Element<T> next;

    public Element(T val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    public Element<T> getNext() {
        return next;
    }
    public T getVal() {
        return val;
    }
}
