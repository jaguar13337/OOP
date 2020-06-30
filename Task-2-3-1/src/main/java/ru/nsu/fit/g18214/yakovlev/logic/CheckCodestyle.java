package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;
import java.util.Formatter;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.gradle.Gradle;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleService;

class CheckCodestyle implements Command {

  private Student student;
  private String taskName;
  private Writer writer;

  CheckCodestyle(Student student, String taskName, Writer writer) {
    this.student = student;
    this.taskName = taskName;
    this.writer = writer;
  }

  @Override
  public void runCommand() throws IOException {
    GradleService gradle = new Gradle();
    if (gradle.checkcodestyle(taskName, student)) {
      writer.write(new Formatter().format("Для задачи %s у студента %s кодстайл соблюден",
        taskName,
        student.getName()).toString());
    } else {
      writer.write(new Formatter().format("Для задачи %s у студента %s кодстайл не соблюден",
        taskName,
        student.getName()).toString());
    }
  }
}

