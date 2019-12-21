package ru.nsu.fit.g18214.yakovlev;

class Credit extends Grade<Boolean> {

  Credit(String nameOfExam, Boolean grade) {
    super(nameOfExam, grade);
  }

  @Override
  int participateInMean() {
    return 0;
  }

  @Override
  int participateInDiploma() {
    if (!getGrade()) {
      return -1;
    }
    return 0;
  }
}
