package ru.nsu.fit.g18214.yakovlev.pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.g18214.yakovlev.analyst.JournalList;
import ru.nsu.fit.g18214.yakovlev.analyst.Statistician;

public class Pizzeria {

  private List<Person> workers;
  private int workingTime;
  private Statistician statistician;

  /**
   * Creates pizzeria with given fields from Config.
   *
   * @param journal where to write journal messages.
   */
  public Pizzeria(PizzeriaConfig config, JournalList journal) {
    this.workingTime = config.getWorkingTime();
    BlockingQueue<Order> orders = new ArrayBlockingQueue<>(config.getQueueCapacity());
    BlockingQueue<Order> storage = new ArrayBlockingQueue<>(config.getStorageCapacity());
    workers = new ArrayList<>();
    for (Integer integer : config.getBakers()) {
      workers.add(new Baker(integer, journal, storage, orders));
    }
    for (CourierConfig courierConfig : config.getCouriers()) {
      workers.add(new Courier(courierConfig, journal, storage));
    }
    workers.add(new OrderGenerator(orders, config.getGeneratorSpeed(), journal));
    statistician = new Statistician(journal, workingTime / 10);
  }

  /**
   * Pizzeria work starts. Pizzeria calls workers to start.
   * Returns statistics in set of strings.
   */
  public Set<String> work() {
    workers.forEach(Person::work);
    try {
      Thread.sleep(workingTime);
    } catch (InterruptedException e) {
      assert (false);
    }
    workers.forEach(Person::stop);

    return statistician.getStats();
  }
}
