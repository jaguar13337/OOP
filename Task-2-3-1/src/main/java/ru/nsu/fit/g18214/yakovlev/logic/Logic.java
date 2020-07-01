package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.Writer;
import java.util.Map;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class Logic implements CommandFactoty {

  private final String[] headers = {"Build", "Style", "Doc", "Tests", "Credit"};
  private Writer writer;

  public Logic(Writer writer) {
    this.writer = writer;
  }

  @Override
  public Command findCommand(Config config, Map<String, String> args) {
    Utils utils = new Utils(headers, config);
    switch (args.get("task")) {
      case "fullReport":
        if (!args.keySet().contains("-g")) {
          throw new IllegalArgumentException();
        }
        return new FullReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "attendanceOnly":
        if (!args.keySet().contains("-g")) {
          throw new IllegalArgumentException();
        }
        return new AttendanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "performanceOnly":
        if (!args.keySet().contains("-g")) {
          throw new IllegalArgumentException();
        }
        return new PerformanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "codestyle":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalArgumentException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckCodestyle(student, args.get("-t"), writer);
          }
        }
        return null;
      case "tests":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalArgumentException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckTests(student, args.get("-t"), writer);
          }
        }
        return null;
      case "docs":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalArgumentException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckDocumentation(student, args.get("-t"), writer);
          }
        }
        return null;
      case "build":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalArgumentException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckBuild(student, args.get("-t"), writer);
          }
        }
        return null;
      default:
        return null;
    }
  }
}
