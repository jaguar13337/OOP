package ru.nsu.fit.g18214.yakovlev;

import java.util.StringJoiner;
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
    log.logGivenMessage(String.format("[%d] [TAKEN] [BAKER %s]", order.getOrderNum(), uuid.toString()));
    Thread.sleep(timeForOnePizza);
    log.logGivenMessage(String.format("[%d] [COOKED] [BAKER %s]", order.getOrderNum(), uuid.toString()));
    storage.add(order);
    log.logGivenMessage(String.format("[%d] [READY FOR DELIVERY]", order.getOrderNum()));
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
