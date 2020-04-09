package ru.nsu.fit.g18214.yakovlev.pizzeria.workers;

/**
 * Order, which has orderNum.
 */
public class Order {

  private int orderNum;

  int getOrderNum() {
    return orderNum;
  }

  Order(int orderNum) {
    this.orderNum = orderNum;
  }
}
