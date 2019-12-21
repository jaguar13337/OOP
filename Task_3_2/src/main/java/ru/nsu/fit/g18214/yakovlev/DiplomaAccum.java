package ru.nsu.fit.g18214.yakovlev;

class DiplomaAccum {
  private MeanGrade meanGrade;
  private boolean hasThree;

  DiplomaAccum() {
    meanGrade = new MeanGrade();
    hasThree = false;
  }

  void addGrade(Grade grade) {
    meanGrade.addExam(grade);
    if (grade.participateInDiploma() == 3) {
      hasThree = true;
    }
  }

  double getMean() {
    return meanGrade.getMean();
  }

  boolean isHasThree() {
    return hasThree;
  }
}
