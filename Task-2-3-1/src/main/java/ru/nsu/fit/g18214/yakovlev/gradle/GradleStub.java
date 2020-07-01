package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class GradleStub implements GradleService {

  @Override
  public TestsResults runTests(String taskName, Student student)  {
    if (student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1")) {
      TestsResults results = new TestsResults();
      results.setSuccTests(9);
      results.setFailedTests(0);
      results.setSkipedTests(0);
      return results;
    }
    TestsResults results = new TestsResults();
    results.setSuccTests(5);
    results.setFailedTests(3);
    results.setSkipedTests(1);
    results.addError("TEST ERROR");
    return results;
  }

  @Override
  public List<String> generateDocs(String taskName, Student student) {
    if (student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1")) {
      return new ArrayList<>();
    } else {
      List<String> errors = new ArrayList<>();
      errors.add("DOCS ERROR");
      return errors;
    }
  }

  @Override
  public List<String> buildTask(String taskName, Student student) {
    if (student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1")) {
      return new ArrayList<>();
    } else {
      List<String> errors = new ArrayList<>();
      errors.add("BUILD ERROR");
      return errors;
    }
  }

  @Override
  public List<String> checkCodeStyle(String taskName, Student student) {
    if (student.getId().equals("ayakovlev") && taskName.equals("Snake-2-2-1")) {
      return new ArrayList<>();
    } else {
      List<String> errors = new ArrayList<>();
      errors.add("CODE STYLE ERROR");
      return errors;
    }
  }
}
