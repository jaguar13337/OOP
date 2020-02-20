package ru.nsu.fit.g18214.yakovlev;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

class Baker implements Runnable {

  private int timeForOnePizza;
  private transient UUID uuid;
  private transient BlockingQueue<Order> orders;
  private transient BlockingQueue<Order> storage;
  private transient Log log;
  private Thread bakerThread;

  void addPizzeriaParameters(Log log, BlockingQueue<Order> storage, BlockingQueue<Order> orders) {
    this.log = log;
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
      long time;
      try {
        time = System.currentTimeMillis();
        order = orders.take();
        if (System.currentTimeMillis() - time > timeForOnePizza) {
          log.addMessageToJournalAndPrint(State.NEED_MORE_ORDERS, uuid, order.getOrderNum());
        }
      } catch (InterruptedException e) {
        return;
      }
      log.addMessageToJournalAndPrint(State.TAKEN, uuid, order.getOrderNum());
      try {
        Thread.sleep(timeForOnePizza);
      } catch (InterruptedException e) {
        log.addMessageToJournalAndPrint(State.COOKED, uuid, order.getOrderNum());
        return;
      }
      log.addMessageToJournalAndPrint(State.COOKED, uuid, order.getOrderNum());

      time = System.currentTimeMillis();
      storage.add(order);
      if (System.currentTimeMillis() - time > timeForOnePizza) {
        log.addMessageToJournalAndPrint(State.FULL_STORAGE, uuid, order.getOrderNum());
      }
    }
  }
}
