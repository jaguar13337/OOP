package ru.nsu.fit.g18214.yakovlev;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

class Baker implements Runnable {

  private int timeForOnePizza;
  private UUID uuid;
  private BlockingQueue<Order> orders;
  private BlockingQueue<Order> storage;
  private Journal journal;
  private Thread bakerThread;

  void addPizzeriaParameters(
      Journal journal, BlockingQueue<Order> storage, BlockingQueue<Order> orders) {
    this.journal = journal;
    this.storage = storage;
    this.orders = orders;
    this.uuid = UUID.randomUUID();
  }

  void work() {
    bakerThread = new Thread(this);
    bakerThread.start();
  }

  void stop() {
    bakerThread.interrupt();
    try {
      bakerThread.join();
    } catch (InterruptedException e) {
      assert (false);
    }
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      Order order;
      try {
        order = orders.take();
        journal.addRecord(
            new Record(uuid, State.TAKEN, System.currentTimeMillis(), order.getOrderNum()));
      } catch (InterruptedException e) {
        return;
      }
      try {
        Thread.sleep(timeForOnePizza);
        journal.addRecord(
            new Record(uuid, State.COOKED, System.currentTimeMillis(), order.getOrderNum()));
        storage.add(order);
        journal.addRecord(
            new Record(uuid, State.STORED, System.currentTimeMillis(), order.getOrderNum()));
      } catch (InterruptedException e) {
        journal.addRecord(
            new Record(uuid, State.DROPPED, System.currentTimeMillis(), order.getOrderNum()));
        return;
      }
    }
  }
}
