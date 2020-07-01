package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.List;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public interface GradleService {
  TestsResults runTests(String taskName, Student student) throws GradleException;

  List<String> generateDocs(String taskName, Student student) throws GradleException;

  List<String> buildTask(String taskName, Student student) throws GradleException;

  List<String> checkCodeStyle(String taskName, Student student) throws GradleException;
}
