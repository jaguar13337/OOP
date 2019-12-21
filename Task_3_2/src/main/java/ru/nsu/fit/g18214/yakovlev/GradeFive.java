package ru.nsu.fit.g18214.yakovlev;

class GradeFive extends Grade<Integer> {

  GradeFive(String nameOfExam, int grade) {
    super(nameOfExam, grade);
  }

  @Override
  public int participateInMean() {
    return getGrade();
  }

  @Override
  public int participateInDiploma() {
    return getGrade();
  }
}
