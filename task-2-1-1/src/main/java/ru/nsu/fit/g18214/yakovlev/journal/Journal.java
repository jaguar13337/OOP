package ru.nsu.fit.g18214.yakovlev.journal;

public interface Journal extends Iterable<Record> {

  void addRecord(Record record);

}
