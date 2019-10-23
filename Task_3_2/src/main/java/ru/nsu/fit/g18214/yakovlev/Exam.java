package ru.nsu.fit.g18214.yakovlev;

class Exam {
  private int grade;
  private String nameOfExam;

  Exam(int grade, String nameOfExam) {
    this.grade = grade;
    this.nameOfExam = nameOfExam;
  }

  int getGrade() {
    return grade;
  }

  String getNameOfExam() {
    return nameOfExam;
  }
}
