package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length < 3 || args.length > 4) {
      throw new IllegalArgumentException(
          "Need 3 args for working without orderGenerator and 4 otherwise.");
    }
    Pizzeria pizzeria =
        new Pizzeria(
            Integer.parseInt(args[2]),
            Integer.parseInt(args[1]),
            new Journal(),
            new FileReader(args[0]));
    if (args.length > 3) {
      pizzeria.generateOrders(Integer.parseInt(args[3]));
    }
    pizzeria.work();
  }
}
