package ru.nsu.fit.g18214.yakovlev.journal;

import java.util.UUID;

public class Record {
  private UUID uuid;
  private State state;
  private long time;
  private int orderID;

  public Record(UUID uuid, State state, long time, int orderID) {
    this.uuid = uuid;
    this.state = state;
    this.time = time;
    this.orderID = orderID;
  }

  public UUID getUuid() {
    return uuid;
  }

  public State getState() {
    return state;
  }

  public long getTime() {
    return time;
  }

  public int getOrderID() {
    return orderID;
  }
}
