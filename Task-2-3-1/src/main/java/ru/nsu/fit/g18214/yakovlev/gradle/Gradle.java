package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.Random;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class Gradle implements GradleService {

  @Override
  public int[] runTests(String taskName, Student student) {
    return new int[]{9, 1, 2};
  }

  @Override
  public Boolean generateDocs(String taskName, Student student) {
    return true;
  }

  @Override
  public Boolean buildTask(String taskName, Student student) {
    return true;
  }

  @Override
  public Boolean checkcodestyle(String taskName, Student student) {
    return true;
  }
}
