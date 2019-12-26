package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

class MeanGradeAccum {

  private int gradeSum;
  private int count;

  MeanGradeAccum() {
    gradeSum = 0;
    count = 0;
  }

  double getMean() {
    return (double)gradeSum / count;
  }

  int getGradeSum() {
    return gradeSum;
  }

  void setGradeSum(int gradeSum) {
    this.gradeSum = gradeSum;
  }

  int getCount() {
    return count;
  }

  void setCount(int count) {
    this.count = count;
  }
}
