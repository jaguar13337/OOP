package ru.nsu.fit.g18214.yakovlev;

class Element<T> {

  private T val;
  private Element<T> prev;

  public Element<T> getPrev() {
    return prev;
  }

  public void setPrev(Element<T> prev) {
    this.prev = prev;
  }

  Element(T val) {
    this.val = val;
  }

  T getVal() {
    return val;
  }
}
