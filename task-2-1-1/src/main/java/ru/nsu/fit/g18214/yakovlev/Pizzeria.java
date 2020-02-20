package ru.nsu.fit.g18214.yakovlev;

import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Pizzeria {

  private transient BlockingQueue<Order> orders;
  private transient BlockingQueue<Order> storage;
  private List<Baker> bakers;
  private List<Courier> couriers;
  private transient int workingTime;
  private Log log;
  private transient boolean isGen = false;
  private transient OrderGenerator generator;

  /**
   * Creates pizzeria with given fields and returns it.
   *
   * @param workingTime time, which pizzeria will wait until the stop all workers.
   * @param storageCapacity capacity, which storage can contain.
   * @param log where to write log messages.
   * @param reader opened reader on json file with rightly printed params.
   */
  public Pizzeria(int workingTime, int storageCapacity, Log log, Reader reader) {
    this.workingTime = workingTime;
    this.orders = new LinkedBlockingDeque<>();
    this.storage = new ArrayBlockingQueue<>(storageCapacity);
    addEmployees(reader);
    this.log = log;
    couriers.forEach(w -> w.addPizzeriaParameters(log, storage));
    bakers.forEach(w -> w.addPizzeriaParameters(log, storage, orders));
  }

  /**
   * Starts generating order with given period to the orders queue.
   *
   * @param period which must be between the generated orders.
   */
  public void generateOrders(int period) {
    generator = new OrderGenerator(orders, period);
    isGen = true;
  }

  private void addEmployees(Reader reader) {
    Gson gson = new Gson();
    Pizzeria pizzeria = gson.fromJson(reader, Pizzeria.class);
    this.bakers = pizzeria.bakers;
    this.couriers = pizzeria.couriers;
  }

  /**
   * Pizzeria work starts.
   * Pizzeria call bakers and couriers to start.
   */
  public void work() {
    bakers.forEach(Baker::work);
    couriers.forEach(Courier::work);
    if (isGen) {
      generator.start();
    }
    try {
      Thread.sleep(workingTime);
    } catch (InterruptedException e) {
      assert (false);
    }
    if (isGen) {
      generator.stop();
    }
    bakers.forEach(Baker::stop);
    couriers.forEach(Courier::stop);
    log.getStats();
  }
}
