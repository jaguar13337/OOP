package ru.nsu.fit.g18214.yakovlev;

import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Pizzeria {

  private transient BlockingQueue<Order> orders;
  private transient BlockingQueue<Order> storage;
  private List<Baker> bakers;
  private List<Courier> couriers;
  private transient int workingTime;
  private transient boolean isGen = false;
  private transient OrderGenerator generator;

  /**
   * Creates pizzeria with given fields and returns it.
   *
   * @param workingTime time, which pizzeria will wait until the stop all workers.
   * @param storageCapacity capacity, which storage can contain.
   * @param queueCapacity order queue capacity, which it can contain.
   * @param log where to write log messages.
   * @param reader opened reader on json file with rightly printed params.
   */
  public Pizzeria(int workingTime, int storageCapacity, int queueCapacity, Log log, Reader reader) {
    this.workingTime = workingTime;
    this.orders = new ArrayBlockingQueue<>(queueCapacity);
    this.storage = new ArrayBlockingQueue<>(storageCapacity);
    addEmployees(reader);
    couriers.forEach(w -> w.addPizzeriaParameters(log, workingTime, storage));
    bakers.forEach(w -> w.addPizzeriaParameters(log, workingTime, storage, orders));
  }

  /**
   * Starts generating order with given period to the orders queue.
   *
   * @param period which must be between the generated orders.
   */
  public void generateOrders(int period) {
    generator = new OrderGenerator(orders, period);
    isGen = true;
    new Thread(generator).start();
  }

  private void addEmployees(Reader reader) {
    Gson gson = new Gson();
    Pizzeria pizzeria = gson.fromJson(reader, Pizzeria.class);
    this.bakers = pizzeria.bakers;
    this.couriers = pizzeria.couriers;
  }

  /**
   * Work of pizzeria starts.
   *
   * @throws InterruptedException if pizzeria was interrupted.
   */
  public void work() throws InterruptedException {
    bakers.forEach(w -> new Thread(w).start());
    couriers.forEach(w -> new Thread(w).start());

    Thread.sleep(workingTime);

    if (isGen) {
      generator.doStop();
    }

    bakers.forEach(Baker::doStop);
    couriers.forEach(Courier::doStop);
  }
}
