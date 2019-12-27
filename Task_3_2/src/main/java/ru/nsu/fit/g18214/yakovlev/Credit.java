package ru.nsu.fit.g18214.yakovlev;

class Credit extends Grade<Boolean> {

  Credit(String nameOfExam, Boolean grade) {
    super(nameOfExam, grade);
  }

  @Override
  void participateInMean(MeanGradeAccum accum) {}

  @Override
  void participateInDiploma(DiplomaAccum accum) {}
}
