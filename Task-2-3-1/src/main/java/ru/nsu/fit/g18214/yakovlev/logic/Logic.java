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
        return new FullReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "attendanceOnly":
        return new AttendanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "performanceOnly":
        return new PerformanceReport(Integer.parseInt(args.get("-g")),
          writer,
          utils);
      case "codestyle":
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckCodestyle(student, args.get("-t"), writer);
          }
        }
        return null;
      case "tests":
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckTests(student, args.get("-t"), writer);
          }
        }
        return null;
      case "docs":
        for (Student student : utils.findGroup(Integer.parseInt(args.get("-g"))).getStudents()) {
          if (student.getId().equals(args.get("-s"))) {
            return new CheckDocumentation(student, args.get("-t"), writer);
          }
        }
        return null;
      case "build":
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
