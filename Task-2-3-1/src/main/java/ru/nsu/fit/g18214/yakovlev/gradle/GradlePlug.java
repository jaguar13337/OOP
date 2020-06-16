package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.Random;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.StudentDSL;

public class GradlePlug implements GradleCalls {
  private Random random = new Random();

  @Override
  public int[] runTests(String taskName, StudentDSL student) {
    return new int[]{random.nextInt(10), random.nextInt(10), random.nextInt(10)};
  }

  @Override
  public Boolean generateDocumentation(String taskName, StudentDSL student) {
    return random.nextBoolean();
  }

  @Override
  public Boolean compileTask(String taskName, StudentDSL student) {
    return random.nextBoolean();
  }

  @Override
  public Boolean checkCodeStyle(String taskName, StudentDSL student) {
    return random.nextBoolean();
  }
}
