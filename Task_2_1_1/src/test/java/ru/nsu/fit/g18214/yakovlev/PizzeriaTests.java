package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.analyst.JournalList;
import ru.nsu.fit.g18214.yakovlev.pizzeria.Pizzeria;
import ru.nsu.fit.g18214.yakovlev.pizzeria.PizzeriaConfig;

public class PizzeriaTests {
  @Test
  public void bakerTest() throws FileNotFoundException {
    Pizzeria pizzeria = new Pizzeria(PizzeriaConfig.makeConfig(
      new FileReader(getClass().getClassLoader().getResource("bakerTest.json").getFile())),
      new JournalList());
    Set<String> bakerStats = pizzeria.work();
    for (String string : bakerStats) {
      Assert.assertTrue(string.endsWith("took [2] finished [1] and dropped [1] orders."));
    }
  }

  @Test
  public void courierTest() throws FileNotFoundException {
    Pizzeria pizzeria = new Pizzeria(PizzeriaConfig.makeConfig(
      new FileReader(getClass().getClassLoader().getResource("courierTest.json").getFile())),
      new JournalList());
    Set<String> allStats = pizzeria.work();
    for (String stats : allStats) {
      if (stats.endsWith("took [1] finished [1] and dropped [0] orders.")) {
        return;
      }
    }
    Assert.fail();
  }
}