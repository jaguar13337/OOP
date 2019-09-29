package ru.nsu.fit.g18214.yakovlev;

class Element<T> {

    private T val;
    private Element<T> next;

    Element(T val) {
        this.val = val;
        this.next = null;
    }

    void setNext(Element<T> next) {
        this.next = next;
    }

    Element<T> getNext() {
        return next;
    }

    T getVal() {
        return val;
    }

}
