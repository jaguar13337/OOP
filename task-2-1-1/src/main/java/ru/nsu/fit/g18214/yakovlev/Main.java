package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length != 1 || !args[0].endsWith(".json")) {
      System.out.println("Need JSON configuration file only.");
      return;
    }
    Pizzeria pizzeria =
        new Pizzeria(
            PizzeriaConfig.makeConfig(
                new FileReader(
                    args[0])),
            new JournalList());
    pizzeria.work();
  }
}
