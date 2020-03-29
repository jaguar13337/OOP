package ru.nsu.fit.g18214.yakovlev.analyst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.g18214.yakovlev.journal.Journal;
import ru.nsu.fit.g18214.yakovlev.journal.Record;
/**
 * Journal realisation on list.
 * Iterable isn't thread safe, so you must iterate only, when records stopped added.
 */
public class JournalList implements Journal, Iterable<Record> {

  @Override
  public Iterator<Record> iterator() {
    return stats.iterator();
  }

  private List<Record> stats;

  public JournalList() {
    stats = new LinkedList<>();
  }

  @Override
  public synchronized void addRecord(Record record) {
    stats.add(record);
  }

}
