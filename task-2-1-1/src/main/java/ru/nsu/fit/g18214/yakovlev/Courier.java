package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

class Courier implements Runnable {

  private int luggageCapacity;
  private int deliveryTime;
  private BlockingQueue<Order> storage;
  private Journal journal;
  private UUID uuid;
  private Thread courierThread;
  private List<Order> orders = new ArrayList<>();

  void addPizzeriaParameters(Journal journal, BlockingQueue<Order> storage) {
    this.journal = journal;
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
    orders.forEach(
        w ->
            journal.addRecord(
                new Record(uuid, State.DROPPED, System.currentTimeMillis(), w.getOrderNum())));
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      while (orders.size() < luggageCapacity) {
        try {
          Order order = storage.take();
          journal.addRecord(
              new Record(uuid, State.TAKEN, System.currentTimeMillis(), order.getOrderNum()));
          orders.add(order);
        } catch (InterruptedException e) {
          dropOrders();
          return;
        }
      }

      while (!orders.isEmpty()) {
        try {
          Thread.sleep(deliveryTime);
          journal.addRecord(
              new Record(
                  uuid,
                  State.DELIVERED,
                  System.currentTimeMillis(),
                  orders.remove(orders.size() - 1).getOrderNum()));
        } catch (InterruptedException e) {
          dropOrders();
          return;
        }
      }
    }
  }
}
