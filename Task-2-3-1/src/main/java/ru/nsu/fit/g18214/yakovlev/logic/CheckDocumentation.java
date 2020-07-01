package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;
import java.util.Formatter;
import java.util.List;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleException;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleService;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleStub;

public class CheckDocumentation implements Command {

  private Student student;
  private String taskName;
  private Writer writer;

  CheckDocumentation(Student student, String taskName, Writer writer) {
    this.student = student;
    this.taskName = taskName;
    this.writer = writer;
  }

  @Override
  public void runCommand() throws IOException {
    GradleService gradle = new GradleStub();
    List<String> errors = null;
    try {
      errors = gradle.generateDocs(taskName, student);
    } catch (GradleException e) {
      assert false;
    }
    if (errors.size() == 0) {
      writer.write(new Formatter().format("Для задачи %s у студента %s документация " +
          " сгенерирована успешно.",
        taskName,
        student.getName()).toString());
    } else {
      writer.write(new Formatter().format("Для задачи %s у студента %s генерация " +
          " документации провалена",
        taskName,
        student.getName()).toString());
      for (String error : errors) {
        writer.write(error);
      }
    }
  }
}
