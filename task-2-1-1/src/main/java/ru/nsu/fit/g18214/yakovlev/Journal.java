package ru.nsu.fit.g18214.yakovlev;

public interface Journal extends Iterable<Record> {

  void addRecord(Record record);

  Record getRecord(int num);

  int getRecordsCount();
}
