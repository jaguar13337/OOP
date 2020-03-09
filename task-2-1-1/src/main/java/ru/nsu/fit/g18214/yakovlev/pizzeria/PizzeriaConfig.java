package ru.nsu.fit.g18214.yakovlev.pizzeria;

import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;

public class PizzeriaConfig {
  private Integer workingTime;
  private Integer storageCapacity;
  private Integer queueCapacity;
  private Integer generatorSpeed;
  private List<Integer> bakersValues;
  private List<CourierConfig> couriers;

  int getGeneratorSpeed()  {
    if (generatorSpeed == null) {
      throw new IllegalArgumentException();
    }
    return generatorSpeed;
  }

  int getWorkingTime() {
    if (workingTime == null) {
      throw new IllegalArgumentException();
    }
    return workingTime;
  }

  int getStorageCapacity() {
    if (storageCapacity == null) {
      throw new IllegalArgumentException();
    }
    return storageCapacity;
  }

  int getQueueCapacity() {
    if (queueCapacity == null) {
      throw new IllegalArgumentException();
    }
    return queueCapacity;
  }

  List<Integer> getBakers() {
    if (bakersValues == null) {
      throw new IllegalArgumentException();
    }
    return bakersValues;
  }

  List<CourierConfig> getCouriers() {
    if (couriers == null) {
      throw new IllegalArgumentException();
    }
    return couriers;
  }

  public static PizzeriaConfig makeConfig(Reader reader) {
    Gson gson = new Gson();
    return gson.fromJson(reader, PizzeriaConfig.class);
  }
}
