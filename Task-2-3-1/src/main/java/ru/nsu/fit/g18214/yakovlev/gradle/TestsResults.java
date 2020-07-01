package ru.nsu.fit.g18214.yakovlev.gradle;

import java.util.ArrayList;
import java.util.List;

public class TestsResults {
  private List<String> errors;
  private int succTests;
  private int skipedTests;
  private int failedTests;

  TestsResults() {
    this.errors = new ArrayList<>();
    this.succTests = 0;
    this.skipedTests = 0;
    this.failedTests = 0;
  }

  public List<String> getErrors() {
    return errors;
  }

  void addError(String error) {
    errors.add(error);
  }

  public int getSuccTests() {
    return succTests;
  }

  void setSuccTests(int succTests) {
    this.succTests = succTests;
  }

  public int getSkipedTests() {
    return skipedTests;
  }

  void setSkipedTests(int skipedTests) {
    this.skipedTests = skipedTests;
  }

  public int getFailedTests() {
    return failedTests;
  }

  void setFailedTests(int failedTests) {
    this.failedTests = failedTests;
  }
}
