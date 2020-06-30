package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.Random;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class Gradle implements GradleService {
  private Random random = new Random();

  @Override
  public int[] runTests(String taskName, Student student) {
    return new int[]{random.nextInt(10), random.nextInt(10), random.nextInt(10)};
  }

  @Override
  public Boolean generateDocumentation(String taskName, Student student) {
    return random.nextBoolean();
  }

  @Override
  public Boolean compileTask(String taskName, Student student) {
    return random.nextBoolean();
  }

  @Override
  public Boolean checkCodeStyle(String taskName, Student student) {
    return random.nextBoolean();
  }
}
