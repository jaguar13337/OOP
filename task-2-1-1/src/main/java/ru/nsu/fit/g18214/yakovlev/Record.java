package ru.nsu.fit.g18214.yakovlev;

import java.util.UUID;

class Record {
  private UUID uuid;
  private State state;
  private long time;
  private int orderID;

  Record(UUID uuid, State state, long time, int orderID) {
    this.uuid = uuid;
    this.state = state;
    this.time = time;
    this.orderID = orderID;
  }

  Record(State state, long time) {
    this.state = state;
    this.time = time;
  }

  UUID getUuid() {
    return uuid;
  }

  State getState() {
    return state;
  }

  long getTime() {
    return time;
  }

  int getOrderID() {
    return orderID;
  }
}
