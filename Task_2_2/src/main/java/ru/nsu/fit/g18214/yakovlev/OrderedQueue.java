package ru.nsu.fit.g18214.yakovlev;

public class OrderedQueue<K extends Comparable<K>,T extends Comparable<T>> {

    private Element<K,T> head;

    /**
     * Generates new empty queue
     */
    public OrderedQueue() {
        this.head = new Element<>(null,null);
    }

    /**
     * Add an element to the queue
     * @param key which is determine place of element
     * @param value element value
     */
    public void insert(K key, T value) {
        Element <K,T> element = new Element<>(key, value);
        Element <K,T> tmp = head;
        while(tmp.getNext() != null)
            tmp = tmp.getNext();
        element.setPrev(tmp);
        tmp.setNext(element);
    }

    /**
     * returns element with highest key
     * @return T value of element
     */
    public T extractMax() {
        Element <K,T> tmp = head.getNext();
        if(tmp == null)
            return null;
        Element <K,T> maximal = tmp;
        while(tmp != null) {
            if (maximal.compareTo(tmp)<0)
                maximal = tmp;
            tmp = tmp.getNext();
        }
        maximal.getPrev().setNext(maximal.getNext());
        if(maximal.getNext() != null)
            maximal.getNext().setPrev(maximal.getPrev());
        return maximal.getValue();
    }
}
