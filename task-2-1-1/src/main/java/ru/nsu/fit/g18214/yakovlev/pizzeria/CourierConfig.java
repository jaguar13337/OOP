package ru.nsu.fit.g18214.yakovlev.pizzeria;

class CourierConfig {

  private int deliveryTime;
  private int luggageCapacity;

  int getDeliveryTime() {
    return deliveryTime;
  }

  public CourierConfig(int deliveryTime, int luggageCapacity) {
    this.deliveryTime = deliveryTime;
    this.luggageCapacity = luggageCapacity;
  }

  int getLuggageCapacity() {
    return luggageCapacity;
  }
}
