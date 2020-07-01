package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.Writer;
import java.util.Map;
import ru.nsu.fit.g18214.yakovlev.IllegalTaskException;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Task;

public class Logic implements CommandFactoty {

  private final String[] headers = {"Build", "Style", "Doc", "Tests", "Credit"};
  private Writer writer;

  public Logic(Writer writer) {
    this.writer = writer;
  }

  @Override
  public Command findCommand(Config config, Map<String, String> args) throws IllegalTaskException {
    Utils utils = new Utils(headers, config);
    switch (args.get("task")) {
      case "fullReport":
        if (!args.keySet().contains("-g")) {
          throw new IllegalTaskException();
        }
        return new FullReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "attendanceOnly":
        if (!args.keySet().contains("-g")) {
          throw new IllegalTaskException();
        }
        return new AttendanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "performanceOnly":
        if (!args.keySet().contains("-g")) {
          throw new IllegalTaskException();
        }
        return new PerformanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "codestyle":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalTaskException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            for (Task task : config.getTasks()) {
              if (task.getName().equals(args.get("-t"))) {
                return new CheckCodestyle(student, args.get("-t"), writer);
              }
            }
            break;
          }
        }
        return null;
      case "tests":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalTaskException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            for (Task task : config.getTasks()) {
              if (task.getName().equals(args.get("-t"))) {
                return new CheckTests(student, args.get("-t"), writer);
              }
            }
            break;
          }
        }
        return null;
      case "docs":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalTaskException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            for (Task task : config.getTasks()) {
              if (task.getName().equals(args.get("-t"))) {
                return new CheckDocumentation(student, args.get("-t"), writer);
              }
            }
            break;
          }
        }
        return null;
      case "build":
        if (!args.keySet().contains("-g")
          || !args.keySet().contains("-s")
          || !args.keySet().contains("-t")) {
          throw new IllegalTaskException();
        }
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            for (Task task : config.getTasks()) {
              if (task.getName().equals(args.get("-t"))) {
                return new CheckBuild(student, args.get("-t"), writer);
              }
            }
            break;
          }
        }
        return null;
      default:
        return null;
    }
  }
}
