package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Courier implements Runnable {

  private int luggageCapacity;
  private int deliveryTime;
  private transient BlockingQueue<Order> storage;
  private transient Log log;
  private transient int workingTime;
  private transient UUID uuid;
  private transient boolean doStop = false;

  synchronized void doStop() {
    this.doStop = true;
  }

  private synchronized boolean keepRunning() {
    return !this.doStop;
  }

  void addPizzeriaParameters(Log log, int workingTime, BlockingQueue<Order> storage) {
    this.log = log;
    this.workingTime = workingTime;
    this.storage = storage;
    this.uuid = UUID.randomUUID();
  }

  private void delivery() throws InterruptedException {

    List<Order> orders = new ArrayList<>();

    while (orders.size() < luggageCapacity) {
      Order order = storage.poll(workingTime, TimeUnit.MILLISECONDS);
      if (order == null) {
        break;
      }
      log.logGivenMessage(
          String.format("[%d] [TAKEN] [Courier %s]", order.getOrderNum(), uuid.toString()));
      orders.add(order);
    }

    while (!orders.isEmpty()) {
      Thread.sleep(deliveryTime);
      log.logGivenMessage(
          String.format(
              "[%d] [DELIVERED] [Courier %s]",
              orders.get(orders.size() - 1).getOrderNum(), uuid.toString()));
      orders.remove(orders.size() - 1);
    }

    Thread.sleep(deliveryTime);
  }

  @Override
  public void run() {
    while (keepRunning()) {
      try {
        delivery();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
