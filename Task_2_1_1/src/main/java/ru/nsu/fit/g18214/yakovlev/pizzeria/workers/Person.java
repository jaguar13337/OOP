package ru.nsu.fit.g18214.yakovlev.pizzeria.workers;

/**
 * Person class, has two methods for start and end work of pizzeria workers.
 * Implemented on threads.
 */
public abstract class Person {

  private Thread currentThread;

  protected abstract void myJob();

  /**
   * Creates new thread and starts work.
   */
  public void work() {
    currentThread = new Thread(this::myJob);
    currentThread.start();
  }

  /**
   * Interrupted thread and joined him.
   */
  public void stop() {
    currentThread.interrupt();
    try {
      currentThread.join();
    } catch (InterruptedException e) {
      assert (false);
    }
  }
}
