package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

class Session {
  private DiplomaAccum accum;

  Session() {
    this.accum = new DiplomaAccum();
  }

  boolean isHasThree() {
    return accum.isHasThree();
  }

  double getMeanGradeInThisSession() {
    return accum.getMean();
  }
  void addExam(Grade grade) {
      accum.addGrade(grade);
  }
}
