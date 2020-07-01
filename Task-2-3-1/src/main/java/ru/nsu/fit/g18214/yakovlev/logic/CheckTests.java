package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;
import java.util.Formatter;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleException;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleService;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleStub;
import ru.nsu.fit.g18214.yakovlev.gradle.TestsResults;

class CheckTests implements Command {

  private Student student;
  private String taskName;
  private Writer writer;

  CheckTests(Student student, String taskName, Writer writer) {
    this.student = student;
    this.taskName = taskName;
    this.writer = writer;
  }

  @Override
  public void runCommand() throws IOException {
    GradleService gradle = new GradleStub();
    TestsResults tests = null;

    try {
      tests = gradle.runTests(taskName, student);
    } catch (GradleException e) {
      assert false;
    }
    if (tests.getErrors().size() == 0) {
      writer.write(new Formatter().format("Для задачи %s у студента %s пройдено %d," +
          " пропущено %d, провалено %d тестов.",
        taskName,
        student.getName(),
        tests.getSuccTests(),
        tests.getSkipedTests(),
        tests.getFailedTests()).toString());
    } else {
      writer.write(new Formatter().format("Для задачи %s у студента %s пройдено %d," +
          " пропущено %d, провалено %d тестов.",
        taskName,
        student.getName(),
        tests.getSuccTests(),
        tests.getSkipedTests(),
        tests.getFailedTests()).toString());
      for (String error : tests.getErrors()) {
        writer.write(error);
      }
    }
  }
}
