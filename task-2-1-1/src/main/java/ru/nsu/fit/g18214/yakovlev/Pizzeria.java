package ru.nsu.fit.g18214.yakovlev;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Pizzeria {

  private BlockingQueue<Order> orders;
  private BlockingQueue<Order> storage;
  private List<Baker> bakers;
  private List<Courier> couriers;
  private int workingTime;
  private Journal journal;
  private OrderGenerator generator;
  private Statistician statistician;

  /**
   * Creates pizzeria with given fields and returns it.
   *
   * @param journal where to write journal messages.
   */
  public Pizzeria(PizzeriaConfig config, Journal journal) {
    this.workingTime = config.getWorkingTime();
    this.orders = new ArrayBlockingQueue<>(config.getQueueCapacity());
    this.storage = new ArrayBlockingQueue<>(config.getStorageCapacity());
    this.journal = journal;
    bakers = config.getBakers();
    couriers = config.getCouriers();
    couriers.forEach(w -> w.addPizzeriaParameters(journal, storage));
    bakers.forEach(w -> w.addPizzeriaParameters(journal, storage, orders));
    generator = new OrderGenerator(orders, config.getGeneratorSpeed());
    statistician = new Statistician(journal, workingTime / 10);
  }

  /** Pizzeria work starts. Pizzeria call bakers and couriers to start. */
  public void work() {
    bakers.forEach(Baker::work);
    couriers.forEach(Courier::work);
    generator.start();
    try {
      Thread.sleep(workingTime);
    } catch (InterruptedException e) {
      assert (false);
    }
    generator.stop();
    bakers.forEach(Baker::stop);
    couriers.forEach(Courier::stop);
    statistician.getStats();
  }
}
