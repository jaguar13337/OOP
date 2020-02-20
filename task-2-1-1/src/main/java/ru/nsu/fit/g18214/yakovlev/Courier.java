package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

class Courier implements Runnable {

  private int luggageCapacity;
  private int deliveryTime;
  private transient BlockingQueue<Order> storage;
  private transient Log log;
  private transient UUID uuid;
  private Thread courierThread;
  private List<Order> orders = new ArrayList<>();

  void addPizzeriaParameters(Log log, BlockingQueue<Order> storage) {
    this.log = log;
    this.storage = storage;
    this.uuid = UUID.randomUUID();
  }

  void work() {
    courierThread = new Thread(this);
    courierThread.start();
  }

  void stop() {
    courierThread.interrupt();
    try {
      courierThread.join();
    } catch (InterruptedException e) {
      assert (false);
    }
  }

  private void dropOrders() {
    orders.forEach(w -> log.addMessageToJournalAndPrint(State.DROPPED, uuid, w.getOrderNum()));
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      while (orders.size() < luggageCapacity) {
        Order order;
        try {
          order = storage.take();
        } catch (InterruptedException e) {
          dropOrders();
          return;
        }
        log.addMessageToJournalAndPrint(State.TAKEN, uuid, order.getOrderNum());
        orders.add(order);
      }

      while (!orders.isEmpty()) {
        try {
          Thread.sleep(deliveryTime);
        } catch (InterruptedException e) {
          dropOrders();
          return;
        }
        log.addMessageToJournalAndPrint(State.DELIVERED, uuid, orders.get(orders.size() - 1).getOrderNum());
        orders.remove(orders.size() - 1);
      }
    }
  }
}
