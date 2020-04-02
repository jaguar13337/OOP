package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import ru.nsu.fit.g18214.yakovlev.analyst.JournalList;
import ru.nsu.fit.g18214.yakovlev.pizzeria.Pizzeria;
import ru.nsu.fit.g18214.yakovlev.pizzeria.PizzeriaConfig;

public class Main {
  public static void main(String[] args) {
    if (args.length != 1 || !args[0].endsWith(".json")) {
      System.out.println("Need JSON configuration file only.");
      return;
    }
    try {
      Pizzeria pizzeria =
          new Pizzeria(PizzeriaConfig.makeConfig(new FileReader(args[0])), new JournalList());
      for (String stat : pizzeria.work()) {
        System.out.println(stat);
      }
    } catch (FileNotFoundException e) {
      System.out.println(String.format("File %s doesn't exist", args[0]));
    } catch (IllegalArgumentException e) {
      System.out.println(String.format("JSON config %s isn't full", args[0]));
    }
  }
}
