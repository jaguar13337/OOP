package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

class Session {

  private ArrayList <Grade> grades;

  Session() {
    this.grades = new ArrayList<>();
  }

  public ArrayList<Grade> getGrades() {
    return grades;
  }

  void addExam(Grade grade) {
      grades.add(grade);
  }
}
