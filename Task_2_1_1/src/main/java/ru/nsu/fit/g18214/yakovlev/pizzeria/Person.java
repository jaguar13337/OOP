package ru.nsu.fit.g18214.yakovlev.pizzeria;

abstract class Person {

  private Thread currentThread;

  abstract void myJob();

  void work() {
    currentThread = new Thread(this::myJob);
    currentThread.start();
  }

  void stop() {
    currentThread.interrupt();
    try {
      currentThread.join();
    } catch (InterruptedException e) {
      assert (false);
    }
  }
}
