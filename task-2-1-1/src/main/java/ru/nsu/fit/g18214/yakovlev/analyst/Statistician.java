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

  private Set<String> strings;
  private Map<UUID, Statistics> statisticsMap;

  public Statistician(JournalList journal, long maxDiff) {
    statisticsMap = new HashMap<>();
    this.journal = journal;
    this.maxDiff = maxDiff;
    this.strings = new TreeSet<>();
  }

  public Set<String> getStats() {
    for (Record record : journal) {
      statisticsMap.putIfAbsent(record.getUuid(), new Statistics());
      switch (record.getState()) {
        case TAKEN:
          statisticsMap.get(record.getUuid()).addTaken();
          if (statisticsMap.get(record.getUuid()).getLastDoingTime() != 0L
              && record.getTime() - statisticsMap.get(record.getUuid()).getLastDoingTime()
                  > maxDiff) {
            strings.add(String.format(firedTip, record.getUuid()));
          }
          break;
        case COOKED:
        case DELIVERED:
          statisticsMap.get(record.getUuid()).addFinished();
          break;
        case STORED:
          if (record.getTime() - statisticsMap.get(record.getUuid()).getLastDoingTime() > maxDiff) {
            strings.add(storageTip);
          }
          break;
        case DROPPED:
          statisticsMap.get(record.getUuid()).addDropped();
          break;
        case QUEUED:
          if (record.getTime() - statisticsMap.get(record.getUuid()).getLastDoingTime()
              > maxDiff) {
            strings.add(hiredTIp);
          }
          break;
        case GENERATED:
          statisticsMap.get(record.getUuid()).setLastDoingTime(record.getTime());
          break;
        default:
          throw new IllegalArgumentException(record.getState().toString());
      }

      statisticsMap.get(record.getUuid()).setLastDoingTime(record.getTime());
    }

    for (UUID uuid : statisticsMap.keySet()) {
      strings.add(
          String.format(
              "[WORKER %s] took [%d] finished [%d] and dropped [%d] orders.",
              uuid.toString(),
              statisticsMap.get(uuid).getTakenCount(),
              statisticsMap.get(uuid).getFinishedCount(),
              statisticsMap.get(uuid).getDroppedCount()));
    }

    return strings;
  }
}
