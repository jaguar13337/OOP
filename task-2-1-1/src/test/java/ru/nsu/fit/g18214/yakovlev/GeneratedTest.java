package ru.nsu.fit.g18214.yakovlev;

import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class GeneratedTest {
  @Test
  public void generatedTest() throws InterruptedException, IOException {
    Pizzeria pizzeria =
      new Pizzeria(
        6000,
        50,
        50,
        new LoggingInStdout(),
        new FileReader(getClass().getClassLoader().getResource("test.json").getFile()));
    pizzeria.generateOrders(300);
    pizzeria.work();
  }
}
