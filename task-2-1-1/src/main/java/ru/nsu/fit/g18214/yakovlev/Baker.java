package ru.nsu.fit.g18214.yakovlev;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Baker implements Runnable {

  private int timeForOnePizza;
  private transient UUID uuid;
  private transient boolean doStop = false;
  private transient BlockingQueue<Order> orders;
  private transient BlockingQueue<Order> storage;
  private transient Log log;
  private transient int workingTime;

  synchronized void doStop() {
    this.doStop = true;
  }

  private synchronized boolean keepRunning() {
    return !this.doStop;
  }

  void addPizzeriaParameters(
      Log log, int workingTime, BlockingQueue<Order> storage, BlockingQueue<Order> orders) {
    this.log = log;
    this.workingTime = workingTime;
    this.storage = storage;
    this.orders = orders;
    this.uuid = UUID.randomUUID();
  }

  private void bake() throws InterruptedException {
    Order order = orders.poll(workingTime, TimeUnit.MILLISECONDS);
    if (order == null) {
      return;
    }
    log.logGivenMessage("[" + order.getOrderNum() + "] [TAKEN] [BAKER " + uuid + "]");
    Thread.sleep(timeForOnePizza);
    log.logGivenMessage("[" + order.getOrderNum() + "] [COOKED] [BAKER " + uuid + "]");
    storage.add(order);
    log.logGivenMessage("[" + order.getOrderNum() + "] [READY FOR DELIVERY]");
  }

  @Override
  public void run() {
    while (keepRunning()) {
      try {
        bake();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
