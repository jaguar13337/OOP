package ru.nsu.fit.g18214.yakovlev;

class Element<T> {

  private T val;
  private Element<T> next;
  private Element<T> prev;

  public Element<T> getPrev() {
    return prev;
  }

  public void setPrev(Element<T> prev) {
    this.prev = prev;
  }

  Element(T val) {
    this.val = val;
    this.next = null;
  }

  void setNext(Element<T> next) {
    this.next = next;
  }

  T getVal() {
    return val;
  }
}
