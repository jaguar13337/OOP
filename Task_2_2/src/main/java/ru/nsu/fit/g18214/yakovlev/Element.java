package ru.nsu.fit.g18214.yakovlev;

class Element<K extends Comparable<K>, T extends Comparable<T>> implements Comparable<Element<K,T>> {
    private T value;
    private K key;
    private Element<K,T> next;
    private Element<K,T> prev;

    @Override
    public int compareTo(Element<K,T> o) {
        return this.key.compareTo(o.getKey());
    }

    Element<K, T> getPrev() {
        return prev;
    }

    void setPrev(Element<K, T> prev) {
        this.prev = prev;
    }

    Element(K key, T value) {
        this.value = value;
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    T getValue() {
        return value;
    }

    K getKey() {
        return key;
    }

    Element<K, T> getNext() {
        return next;
    }

    void setNext(Element<K, T> next) {
        this.next = next;
    }
}
