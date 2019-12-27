package ru.nsu.fit.g18214.yakovlev;

class GradeFive extends Grade<Integer> {

  GradeFive(String nameOfExam, int grade) {
    super(nameOfExam, grade);
  }

  @Override
  void participateInMean(MeanGradeAccum accum) {
    accum.addGradeToSum(getGrade());
  }

  @Override
  void participateInDiploma(DiplomaAccum accum) {
    if (getGrade() == 3) {
      accum.setImpossibleToGetRedDiploma(true);
    }
    participateInMean(accum.getMeanGradeAccum());
  }
}
