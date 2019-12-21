package ru.nsu.fit.g18214.yakovlev;

abstract class Grade <T> {

  private String nameOfExam;
  private T grade;

  Grade(String nameOfExam, T grade) {
    this.nameOfExam = nameOfExam;
    this.grade = grade;
  }

  T getGrade() {
    return grade;
  }

  String getNameOfExam() {
    return nameOfExam;
  }

  abstract int participateInMean();

  abstract int participateInDiploma();

}
