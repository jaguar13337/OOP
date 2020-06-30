package ru.nsu.fit.g18214.yakovlev.logic;

import groovy.json.internal.IO;
import java.io.IOException;
import java.io.Writer;
import java.util.Formatter;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.gradle.Gradle;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleService;

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
    GradleService gradle = new Gradle();
    int[] tests = gradle.runTests(taskName, student);
    writer.write(new Formatter().format("Для задачи %s у студента %s пройдено %d," +
        " пропущено %d, провалено %d тестов.",
      taskName,
      student.getName(),
      tests[0],
      tests[1],
      tests[2]).toString());
  }
}
