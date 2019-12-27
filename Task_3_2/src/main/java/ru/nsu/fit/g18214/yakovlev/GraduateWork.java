package ru.nsu.fit.g18214.yakovlev;

class GraduateWork extends Grade<Integer> {

  public GraduateWork(String nameOfExam, Integer grade) {
    super(nameOfExam, grade);
  }

  @Override
  void participateInMean(MeanGradeAccum accum) {}

  @Override
  void participateInDiploma(DiplomaAccum accum) {
    if (getGrade() != 5) {
      accum.setImpossibleToGetRedDiploma(true);
    }
  }
}
