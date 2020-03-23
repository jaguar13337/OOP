package ru.nsu.fit.g18214.yakovlev;

import java.util.Set;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.analyst.JournalList;
import ru.nsu.fit.g18214.yakovlev.analyst.Statistician;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
import ru.nsu.fit.g18214.yakovlev.journal.State;

public class StatisticsTests {
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
