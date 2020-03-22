package ru.nsu.fit.g18214.yakovlev.pizzeria;

public class CourierConfig {

  private int deliveryTime;
  private int luggageCapacity;

  public int getDeliveryTime() {
    return deliveryTime;
  }

  public CourierConfig(int deliveryTime, int luggageCapacity) {
    this.deliveryTime = deliveryTime;
    this.luggageCapacity = luggageCapacity;
  }

  public int getLuggageCapacity() {
    return luggageCapacity;
  }
}
