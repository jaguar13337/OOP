package ru.nsu.fit.g18214.yakovlev;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class Journal implements Log {

  private boolean makeStorageBigger = false;
  private boolean needMoreOrders = false;
  private Map<UUID, Integer[]> stats;
  private Integer[] defVal = new Integer[3];

  Journal() {
    stats = new ConcurrentHashMap<>();
    defVal[0] = 0;
    defVal[1] = 0;
    defVal[2] = 0;
  }

  /**
   * This method add information about order in the journal and prints some log in sout.
   * @param state State of order.
   * @param uuid UUID of worker.
   * @param orderID ID order.
   */
  @Override
  public void addMessageToJournalAndPrint(State state, UUID uuid, int orderID) {
    stats.putIfAbsent(uuid, defVal.clone());
    switch (state) {
      case TAKEN:
        stats.get(uuid)[0]++;
        break;
      case COOKED:
      case DELIVERED:
        stats.get(uuid)[1]++;
        break;
      case DROPPED:
        stats.get(uuid)[2]++;
        break;
      case FULL_STORAGE:
        makeStorageBigger = true;
        break;
      case NEED_MORE_ORDERS:
        needMoreOrders = true;
        break;
      default:
        throw new IllegalArgumentException(state.name());
    }
    System.out.printf("[%d] [%s] by [%s]\n", orderID, state.name(), uuid.toString());
  }

  /**
   * This realisation prints stats in sout and give some simple advises.
   */
  public void getStats() {
    System.out.println("---------------------------------------------");
    stats
        .keySet()
        .forEach(
            w -> {
              System.out.printf("[%s]\n", w.toString());
              System.out.printf("Taken [%d] orders.\n", stats.get(w)[0]);
              System.out.printf("Finished [%d] orders.\n", stats.get(w)[1]);
              System.out.printf("Dropped [%d] orders.\n", stats.get(w)[2]);
              System.out.println("---------------------------------------------");
            });

    if (makeStorageBigger) {
      System.out.println("MAKE STORAGE BIGGER");
    }
    if (needMoreOrders) {
      System.out.println("NOT ENOUGH ORDERS");
    }
  }
}
