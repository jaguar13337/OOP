package ru.nsu.fit.g18214.yakovlev.gradle;

import ru.nsu.fit.g18214.yakovlev.dsl.engine.StudentDSL;

public interface GradleCalls {
  int[] runTests(String taskName, StudentDSL student);

  Boolean generateDocumentation(String taskName, StudentDSL student);

  Boolean compileTask(String taskName, StudentDSL student);

  Boolean checkCodeStyle(String taskName, StudentDSL student);
}
