package ru.nsu.fit.g18214.yakovlev.analyst;

class Statistics {
  private int droppedCount;
  private int takenCount;
  private int finishedCount;
  private long lastDoingTime;
  private boolean generated;

  Statistics() {
    finishedCount = droppedCount = takenCount = 0;
    lastDoingTime = 0L;
  }

  boolean isGenerated() {
    return generated;
  }

  void makeGenerated() {
    this.generated = true;
  }

  void addFinished() {
    finishedCount++;
  }


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
}
