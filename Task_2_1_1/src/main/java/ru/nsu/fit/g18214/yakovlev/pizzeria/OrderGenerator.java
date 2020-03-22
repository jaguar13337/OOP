package ru.nsu.fit.g18214.yakovlev.pizzeria;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.g18214.yakovlev.journal.Journal;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
import ru.nsu.fit.g18214.yakovlev.journal.State;

class OrderGenerator extends Person {

  private BlockingQueue<Order> orders;
  private int orderNumber = 0;
  private int period;
  private Journal journal;
  private UUID uuid;

  @Override
  void myJob() {
    Order order;
    while (!Thread.interrupted()) {
      try {
        order = new Order(orderNumber++);
        journal.addRecord(
            new Record(uuid, State.GENERATED, System.currentTimeMillis(), order.getOrderNum()));
        orders.put(order);
        journal.addRecord(
            new Record(uuid, State.QUEUED, System.currentTimeMillis(), order.getOrderNum()));
        Thread.sleep(period);
      } catch (InterruptedException e) {
        break;
      }
    }
  }

  OrderGenerator(BlockingQueue<Order> orders, int period, Journal journal) {
    this.orders = orders;
    this.period = period;
    this.journal = journal;
    uuid = UUID.randomUUID();
  }
}
