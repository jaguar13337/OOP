package ru.nsu.fit.g18214.yakovlev;

class Statistics {
  private int droppedCount;
  private int takenCount;
  private int finishedCount;
  private long lastDoingTime;

  int getDroppedCount() {
    return droppedCount;
  }

  int getTakenCount() {
    return takenCount;
  }

  int getFinishedCount() {
    return finishedCount;
  }

  long getLastDoingTime() {
    return lastDoingTime;
  }

  void addDropped() {
    droppedCount++;
  }

  void addTaken() {
    takenCount++;
  }

  void setLastDoingTime(long lastDoingTime) {
    this.lastDoingTime = lastDoingTime;
  }

  Statistics() {
    finishedCount = droppedCount = takenCount = 0;
    lastDoingTime = 0L;
  }

  void addFinished() {
    finishedCount++;
  }
}
