package ru.nsu.fit.g18214.yakovlev;

import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;

class PizzeriaConfig {
  private int workingTime;
  private int storageCapacity;
  private int queueCapacity;
  private int generatorSpeed;
  private List<Baker> bakers;
  private List<Courier> couriers;

  int getGeneratorSpeed() {
    return generatorSpeed;
  }

  int getWorkingTime() {
    return workingTime;
  }

  int getStorageCapacity() {
    return storageCapacity;
  }

  int getQueueCapacity() {
    return queueCapacity;
  }

  List<Baker> getBakers() {
    return bakers;
  }

  List<Courier> getCouriers() {
    return couriers;
  }

  static PizzeriaConfig makeConfig(Reader reader) {
    Gson gson = new Gson();
    return gson.fromJson(reader, PizzeriaConfig.class);
  }
}
