package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class Statistician {

  private Journal journal;

  private long maxDiff;

  private List<String> strings;
  private Map<UUID, Statistics> statisticsMap;

  Statistician(Journal journal, long maxDiff) {
    statisticsMap = new HashMap<>();
    this.journal = journal;
    this.maxDiff = maxDiff;
    this.strings = new ArrayList<>();
  }

  void getStats() {
    for (Record record : journal) {
      statisticsMap.putIfAbsent(record.getUuid(), new Statistics());
      switch (record.getState()) {
        case TAKEN:
          statisticsMap.get(record.getUuid()).addTaken();
          if (!strings.contains("WE CAN FIRE SOMEBODY\n")
              && statisticsMap.get(record.getUuid()).getLastDoingTime() != 0L
              && record.getTime() - statisticsMap.get(record.getUuid()).getLastDoingTime()
                  > maxDiff) {
            strings.add("WE CAN FIRE SOMEBODY\n");
          }
          break;
        case COOKED:
        case DELIVERED:
          statisticsMap.get(record.getUuid()).addFinished();
          break;
        case STORED:
          if (!strings.contains("WE NEED BIGGER STORAGE\n")
              && record.getTime() - statisticsMap.get(record.getUuid()).getLastDoingTime()
                  > maxDiff) {
            strings.add("WE NEED BIGGER STORAGE\n");
          }
          break;
        case DROPPED:
          statisticsMap.get(record.getUuid()).addDropped();
          break;
        default:
          throw new IllegalArgumentException(record.getState().toString());
      }

      statisticsMap.get(record.getUuid()).setLastDoingTime(record.getTime());
    }

    for (UUID uuid : statisticsMap.keySet()) {
      System.out.println(
          String.format(
              "[WORKER %s] took [%d] finished [%d] and dropped [%d] orders",
              uuid.toString(),
              statisticsMap.get(uuid).getTakenCount(),
              statisticsMap.get(uuid).getFinishedCount(),
              statisticsMap.get(uuid).getDroppedCount()));
    }

    for (String string : strings) {
      System.out.println(string);
    }
  }
}
