package ru.nsu.fit.g18214.yakovlev.journal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JournalList implements Journal {

  @Override
  public Iterator<Record> iterator() {
    return stats.iterator();
  }

  private List<Record> stats;

  public JournalList() {
    stats = new ArrayList<>();
  }

  @Override
  public synchronized void addRecord(Record record) {
    stats.add(record);
  }
}
