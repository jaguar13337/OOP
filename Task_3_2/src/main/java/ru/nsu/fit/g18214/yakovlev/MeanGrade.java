package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

class MeanGrade {

  private ArrayList<Grade> grades;

  private double gradeSum;
  private int count;

  MeanGrade() {
    gradeSum = 0;
    count = 0;
    grades = new ArrayList<>();
  }

  double getMean() {
    return gradeSum / count;
  }

  void addExam(Grade exam) {
    grades.add(exam);
    int grade = exam.participateInMean();
    if (grade > 0) {
      count++;
      gradeSum += grade;
    }
  }

  ArrayList<Grade> getGrades() {
    return grades;
  }
}
