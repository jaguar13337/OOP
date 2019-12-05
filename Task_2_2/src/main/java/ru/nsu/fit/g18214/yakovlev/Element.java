package ru.nsu.fit.g18214.yakovlev;

class Element<K extends Comparable<K>, T> implements Comparable<Element<K, T>> {
  private T value;
  private K key;
  private Element<K, T> next;

  @Override
  public int compareTo(Element<K, T> o) {
    if (o == null)
      return Integer.MAX_VALUE;
    return this.key.compareTo(o.getKey());
  }

  Element(K key, T value) {
    this.value = value;
    this.key = key;
    this.next = null;
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
