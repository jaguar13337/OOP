package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.analyst.JournalList;
import ru.nsu.fit.g18214.yakovlev.analyst.Statistician;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
import ru.nsu.fit.g18214.yakovlev.journal.State;
import ru.nsu.fit.g18214.yakovlev.pizzeria.PizzeriaConfig;

public class PizzeriaTest {

  @Test
  public void configTest() throws FileNotFoundException {
    try {
      PizzeriaConfig config = PizzeriaConfig.makeConfig(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("test.json")).getFile()));

      Assert.assertEquals(5000, config.getWorkingTime());
      Assert.assertEquals(1, config.getStorageCapacity());
      Assert.assertEquals(50, config.getQueueCapacity());
      Assert.assertEquals(350, config.getGeneratorSpeed());

      Assert.assertEquals(2, config.getBakers().size());
      Assert.assertEquals((Integer) 520, config.getBakers().get(0));
      Assert.assertEquals((Integer) 500, config.getBakers().get(1));

      Assert.assertEquals(2, config.getCouriers().size());
      Assert.assertEquals(2000, config.getCouriers().get(0).getDeliveryTime());
      Assert.assertEquals(1, config.getCouriers().get(0).getLuggageCapacity());
      Assert.assertEquals(2000, config.getCouriers().get(1).getDeliveryTime());
      Assert.assertEquals(1, config.getCouriers().get(1).getLuggageCapacity());
    } catch (FileNotFoundException e) {
      Assert.fail();
    }

    PizzeriaConfig config = PizzeriaConfig.makeConfig(new FileReader(getClass().getClassLoader().getResource("ahah.json").getFile()));
    try {
      config.getCouriers();
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }

  }

  @Test
  public void statsTest() {
    JournalList journal = new JournalList();

    UUID uuid = UUID.randomUUID();
    journal.addRecord(new Record(uuid, State.TAKEN, System.currentTimeMillis(), 0));
    journal.addRecord(new Record(uuid, State.COOKED, System.currentTimeMillis(), 0));
    journal.addRecord(new Record(uuid, State.STORED, System.currentTimeMillis(), 0));


    journal.addRecord(new Record(uuid, State.TAKEN, System.currentTimeMillis(), 1));
    journal.addRecord(new Record(uuid, State.COOKED, System.currentTimeMillis(), 1));
    journal.addRecord(new Record(uuid, State.STORED, System.currentTimeMillis(), 1));

    UUID uuid1 = UUID.randomUUID();
    journal.addRecord(new Record(uuid1, State.TAKEN, System.currentTimeMillis(), 2));
    journal.addRecord(new Record(uuid1, State.COOKED, System.currentTimeMillis(), 2));
    journal.addRecord(new Record(uuid1, State.STORED, System.currentTimeMillis(), 2));


    journal.addRecord(new Record(uuid1, State.TAKEN, System.currentTimeMillis(), 3));
    journal.addRecord(new Record(uuid1, State.DROPPED, System.currentTimeMillis(), 3));

    Statistician statistician = new Statistician(journal, 6000);
    Set set = statistician.getStats();

    Assert.assertTrue(set.contains("[WORKER " + uuid + "] took [2] finished [2] and dropped [0] orders."));
    Assert.assertTrue(set.contains("[WORKER " + uuid1 + "] took [2] finished [1] and dropped [1] orders."));
  }

}
