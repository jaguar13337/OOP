package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class JournalList implements Journal {

  @Override
  public Iterator<Record> iterator() {
    return stats.iterator();
  }

  private List<Record> stats;

  JournalList() {
    stats = new ArrayList<>();
  }

  @Override
  public Record getRecord(int num) {
    return stats.get(num);
  }

  @Override
  public int getRecordsCount() {
    return stats.size();
  }

  @Override
  public void addRecord(Record record) {
    stats.add(record);
  }
}
