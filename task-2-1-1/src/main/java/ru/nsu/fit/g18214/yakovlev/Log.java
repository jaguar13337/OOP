package ru.nsu.fit.g18214.yakovlev;

import java.util.UUID;

public interface Log {

  void addMessageToJournalAndPrint(State state, UUID uuid, int orderID);

  void getStats();
}
