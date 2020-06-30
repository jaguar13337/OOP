package ru.nsu.fit.g18214.yakovlev.gradle;

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public interface GradleService {
  int[] runTests(String taskName, Student student);

  Boolean generateDocs(String taskName, Student student);

  Boolean buildTask(String taskName, Student student);

  Boolean checkcodestyle(String taskName, Student student);
}
