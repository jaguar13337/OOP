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

  public int getGeneratorSpeed() {
    return generatorSpeed;
  }

  public int getWorkingTime() {
    return workingTime;
  }

  public int getStorageCapacity() {
    return storageCapacity;
  }

  public int getQueueCapacity() {
    return queueCapacity;
  }

  public List<Integer> getBakers() {
    return bakersValues;
  }

  public List<CourierConfig> getCouriers() {
    return couriers;
  }

  public static PizzeriaConfig makeConfig(Reader reader) {
    Gson gson = new Gson();
    PizzeriaConfig pizzeriaConfig = gson.fromJson(reader, PizzeriaConfig.class);
    if (pizzeriaConfig.getCouriers() == null ||
      pizzeriaConfig.getWorkingTime() == 0 ||
      pizzeriaConfig.getBakers() == null ||
      pizzeriaConfig.getGeneratorSpeed() == 0 ||
      pizzeriaConfig.getStorageCapacity() == 0 ||
      pizzeriaConfig.getBakers() == null) {
      throw new IllegalArgumentException();
    }
    return pizzeriaConfig;
  }
}
