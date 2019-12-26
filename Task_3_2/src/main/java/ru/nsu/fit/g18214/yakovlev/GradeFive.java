package ru.nsu.fit.g18214.yakovlev;

class GradeFive extends Grade<Integer> {

  GradeFive(String nameOfExam, int grade) {
    super(nameOfExam, grade);
  }

  @Override
  void participateInMean(MeanGradeAccum accum) {
    accum.setGradeSum(accum.getGradeSum()+this.getGrade());
    accum.setCount(accum.getCount()+1);
  }

  @Override
  void participateInDiploma(DiplomaAccum accum) {
    if (getGrade() == 3) {
      accum.setHasThree(true);
    }
    participateInMean(accum.getMeanGradeAccum());
  }
}
