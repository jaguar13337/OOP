package ru.nsu.fit.g18214.yakovlev;

import java.util.concurrent.BlockingQueue;

class OrderGenerator implements Runnable {

  private BlockingQueue<Order> orders;
  private int orderNumber = 0;
  private int period;
  private Thread genThread;

  OrderGenerator(BlockingQueue<Order> orders, int period) {
    this.orders = orders;
    this.period = period;
  }

  void stop() {
    genThread.interrupt();
    try {
      genThread.join();
    } catch (InterruptedException e) {
      assert (false);
    }
  }

  void start() {
    genThread = new Thread(this);
    genThread.start();
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      orders.add(new Order(orderNumber++));
      try {
        Thread.sleep(period);
      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
