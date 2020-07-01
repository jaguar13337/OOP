package ru.nsu.fit.g18214.yakovlev.gradle;

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class Gradle implements GradleService {

  @Override
  public int[] runTests(String taskName, Student student) {
    if (student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1")) {
      return new int[]{9, 0, 0};
    }
    return new int[]{5, 3, 1};
  }

  @Override
  public Boolean generateDocs(String taskName, Student student) {
    return student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1");
  }

  @Override
  public Boolean buildTask(String taskName, Student student) {
    return student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1");
  }

  @Override
  public Boolean checkcodestyle(String taskName, Student student) {
    return student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1");
  }
}
