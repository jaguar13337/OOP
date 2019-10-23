package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

class Session {
  private ArrayList<Exam> session;
  private double averageGradeInThisSession;
  private boolean hasThree;

  Session(String[] namesOfExams, Integer[] grades) {
    if (namesOfExams.length != grades.length) throw new IllegalArgumentException();
    this.session = new ArrayList<>();
    for (int i = 0; i < grades.length; i++) {
      addExam(namesOfExams[i], grades[i]);
      if (grades[i] == 3) hasThree = true;
    }
    averageGradeInThisSession = 0;
  }

  boolean isHasThree() {
    return hasThree;
  }

  double getAverageGradeInThisSession() {
    if (averageGradeInThisSession != 0) return averageGradeInThisSession;
    for (Exam exam : session) averageGradeInThisSession += exam.getGrade();
    averageGradeInThisSession /= session.size();
    return averageGradeInThisSession;
  }

  private void addExam(String nameOfExam, Integer grade) {
    System.out.println(nameOfExam + " " + grade);
    session.add(new Exam(grade, nameOfExam));
  }

  ArrayList<Exam> getSession() {
    return session;
  }
}
