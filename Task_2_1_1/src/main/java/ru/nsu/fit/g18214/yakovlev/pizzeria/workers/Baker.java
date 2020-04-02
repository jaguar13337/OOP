package ru.nsu.fit.g18214.yakovlev.pizzeria.workers;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.g18214.yakovlev.journal.Journal;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
import ru.nsu.fit.g18214.yakovlev.journal.State;

/**
 * Default baker realisation. Works until not interrupted
 */
public class Baker extends Person {

  private int timeForOnePizza;
  private UUID uuid;
  private BlockingQueue<Order> orders;
  private BlockingQueue<Order> storage;
  private Journal journal;

  public Baker(
      int timeForOnePizza,
      Journal journal,
      BlockingQueue<Order> storage,
      BlockingQueue<Order> orders) {
    this.timeForOnePizza = timeForOnePizza;
    this.journal = journal;
    this.storage = storage;
    this.orders = orders;
    this.uuid = UUID.randomUUID();
  }

  @Override
  protected void myJob() {
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
        storage.put(order);
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
