package ru.nsu.fit.g18214.yakovlev.pizzeria;

import java.util.concurrent.BlockingQueue;

class OrderGenerator {

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
    genThread =
        new Thread(
            () -> {
              while (!Thread.interrupted()) {
                orders.add(new Order(orderNumber++));
                try {
                  Thread.sleep(period);
                } catch (InterruptedException e) {
                  break;
                }
              }
            });
    genThread.start();
  }
}
