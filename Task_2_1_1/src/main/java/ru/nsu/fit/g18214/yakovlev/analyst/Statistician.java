package ru.nsu.fit.g18214.yakovlev.analyst;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import ru.nsu.fit.g18214.yakovlev.journal.Record;

public class Statistician {

  private static final String firedTip = "WE CAN FIRE %s";
  private static final String storageTip = "WE NEED BIGGER STORAGE OR HIRE MORE COURIERS";
  private static final String hiredTIp = "WE NEED TO HIRE MORE BAKERS";

  private JournalList journal;

  private long maxDiff;

  private Set<String> statisticsAndAdvises;
  private Map<UUID, Statistics> statisticsByUUIDMap;

  public Statistician(JournalList journal, long maxDiff) {
    this.statisticsByUUIDMap = new HashMap<>();
    this.journal = journal;
    this.maxDiff = maxDiff;
    this.statisticsAndAdvises = new TreeSet<>();
  }


  public Set<String> getStats() {
    for (Record record : journal) {
      statisticsByUUIDMap.putIfAbsent(record.getUuid(), new Statistics());
      switch (record.getState()) {
        case TAKEN:
          statisticsByUUIDMap.get(record.getUuid()).addTaken();
          if (statisticsByUUIDMap.get(record.getUuid()).getLastDoingTime() != 0L
            && record.getTime() - statisticsByUUIDMap.get(record.getUuid()).getLastDoingTime()
            > maxDiff) {
            statisticsAndAdvises.add(String.format(firedTip, record.getUuid()));
          }
          break;
        case COOKED:
        case DELIVERED:
          statisticsByUUIDMap.get(record.getUuid()).addFinished();
          break;
        case STORED:
          if (record.getTime() - statisticsByUUIDMap.get(record.getUuid()).getLastDoingTime() > maxDiff) {
            statisticsAndAdvises.add(storageTip);
          }
          break;
        case DROPPED:
          statisticsByUUIDMap.get(record.getUuid()).addDropped();
          break;
        case QUEUED:
          if (record.getTime() - statisticsByUUIDMap.get(record.getUuid()).getLastDoingTime()
            > maxDiff) {
            statisticsAndAdvises.add(hiredTIp);
          }
          break;
        case GENERATED:
          statisticsByUUIDMap.get(record.getUuid()).makeGenerated();
          statisticsByUUIDMap.get(record.getUuid()).setLastDoingTime(record.getTime());
          break;
        default:
          throw new IllegalArgumentException(record.getState().toString());
      }

      statisticsByUUIDMap.get(record.getUuid()).setLastDoingTime(record.getTime());
    }

    for (UUID uuid : statisticsByUUIDMap.keySet()) {
      if (!statisticsByUUIDMap.get(uuid).isGenerated()) {
        statisticsAndAdvises.add(
          String.format(
            "[WORKER %s] took [%d] finished [%d] and dropped [%d] orders.",
            uuid.toString(),
            statisticsByUUIDMap.get(uuid).getTakenCount(),
            statisticsByUUIDMap.get(uuid).getFinishedCount(),
            statisticsByUUIDMap.get(uuid).getDroppedCount()));
      }
    }
    return statisticsAndAdvises;
  }
}
