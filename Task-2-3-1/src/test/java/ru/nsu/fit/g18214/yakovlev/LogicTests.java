package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Engine;
import ru.nsu.fit.g18214.yakovlev.logic.AttendanceReport;
import ru.nsu.fit.g18214.yakovlev.logic.Command;
import ru.nsu.fit.g18214.yakovlev.logic.FullReport;
import ru.nsu.fit.g18214.yakovlev.logic.Logic;
import ru.nsu.fit.g18214.yakovlev.logic.PerformanceReport;

public class LogicTests {

  @Test
  public void fullReportTestSucc() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "fullReport");
    args.put("-g", "18214");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      command.runCommand();
      Assert.assertTrue(command instanceof FullReport);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void fullReportFail() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "fullReport");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      Assert.fail();
    } catch (FileNotFoundException e) {
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void attendanceTestSucc() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "attendanceOnly");
    args.put("-g", "18214");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      command.runCommand();
      Assert.assertTrue(command instanceof AttendanceReport);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void attendanceTestFail() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "attendanceOnly");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      Assert.fail();
    } catch (FileNotFoundException e) {
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void performanceTestSucc() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "performanceOnly");
    args.put("-g", "18214");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      command.runCommand();
      Assert.assertTrue(command instanceof PerformanceReport);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void performanceTestFail() {
    Map<String, String> args = new HashMap<>();
    args.put("task", "performanceOnly");
    Writer writer = new StringWriter();
    Logic logic = new Logic(writer);
    try {
      Config config = (Config) Engine.executeDSL(getClass()
        .getClassLoader()
        .getResource("config.nsu")
        .getPath());
      Command command = logic.findCommand(config, args);
      Assert.fail();
    } catch (FileNotFoundException e) {
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }
}
