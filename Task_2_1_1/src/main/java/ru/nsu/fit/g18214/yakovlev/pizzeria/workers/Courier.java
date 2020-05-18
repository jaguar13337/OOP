package ru.nsu.fit.g18214.yakovlev.pizzeria.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.g18214.yakovlev.journal.Journal;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
import ru.nsu.fit.g18214.yakovlev.journal.State;
import ru.nsu.fit.g18214.yakovlev.pizzeria.CourierConfig;

/**
 * Default courier realisation. Works until not interrupted
 */
public class Courier extends Person {

  private int luggageCapacity;
  private int deliveryTime;
  private BlockingQueue<Order> storage;
  private Journal journal;
  private UUID uuid;
  private List<Order> orders = new ArrayList<>();


  public Courier(CourierConfig config, Journal journal, BlockingQueue<Order> storage) {
    this.luggageCapacity = config.getLuggageCapacity();
    this.deliveryTime = config.getDeliveryTime();
    this.journal = journal;
    this.storage = storage;
    this.uuid = UUID.randomUUID();
  }

  protected void myJob() {
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

  private void dropOrders() {
    orders.forEach(
      w ->
        journal.addRecord(
          new Record(uuid, State.DROPPED, System.currentTimeMillis(), w.getOrderNum())));
  }
}
