package ru.nsu.fit.g18214.yakovlev;

import java.util.concurrent.BlockingQueue;

class OrderGenerator implements Runnable {

  private boolean doStop = false;

  synchronized void doStop() {
    this.doStop = true;
  }

  private synchronized boolean keepRunning() {
    return !this.doStop;
  }

  private BlockingQueue<Order> orders;
  private int orderNumber = 0;
  private int period;

  OrderGenerator(BlockingQueue<Order> orders, int period) {
    this.orders = orders;
    this.period = period;
  }

  @Override
  public void run() {
    while (keepRunning()) {
      orders.add(new Order(orderNumber++));
      try {
        Thread.sleep(period);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
