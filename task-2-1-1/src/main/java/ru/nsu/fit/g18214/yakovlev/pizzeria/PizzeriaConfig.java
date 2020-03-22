package ru.nsu.fit.g18214.yakovlev.pizzeria;

import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;

/**
 * Creates config file using method make config.
 */
public class PizzeriaConfig {
  private Integer workingTime;
  private Integer storageCapacity;
  private Integer queueCapacity;
  private Integer generatorSpeed;
  private List<Integer> bakersValues;
  private List<CourierConfig> couriers;

  private PizzeriaConfig() {
  }

  public int getGeneratorSpeed()  {
    if (generatorSpeed == null) {
      throw new IllegalArgumentException();
    }
    return generatorSpeed;
  }

  public int getWorkingTime() {
    if (workingTime == null) {
      throw new IllegalArgumentException();
    }
    return workingTime;
  }

  public int getStorageCapacity() {
    if (storageCapacity == null) {
      throw new IllegalArgumentException();
    }
    return storageCapacity;
  }

  public int getQueueCapacity() {
    if (queueCapacity == null) {
      throw new IllegalArgumentException();
    }
    return queueCapacity;
  }

  public List<Integer> getBakers() {
    if (bakersValues == null) {
      throw new IllegalArgumentException();
    }
    return bakersValues;
  }

  public List<CourierConfig> getCouriers() {
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
