package ru.nsu.fit.g18214.yakovlev.gradle;

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public interface GradleService {
  int[] runTests(String taskName, Student student);

  Boolean generateDocumentation(String taskName, Student student);

  Boolean compileTask(String taskName, Student student);

  Boolean checkCodeStyle(String taskName, Student student);
}
