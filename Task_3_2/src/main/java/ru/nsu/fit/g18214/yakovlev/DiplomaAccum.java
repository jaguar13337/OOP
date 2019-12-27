package ru.nsu.fit.g18214.yakovlev;

class DiplomaAccum {
  private MeanGradeAccum meanGradeAccum;
  private boolean impossibleToGetRedDiploma;

  DiplomaAccum() {
    meanGradeAccum = new MeanGradeAccum();
    impossibleToGetRedDiploma = false;
  }

  MeanGradeAccum getMeanGradeAccum() {
    return meanGradeAccum;
  }

  void setImpossibleToGetRedDiploma(boolean hasThree) {
    this.impossibleToGetRedDiploma = hasThree;
  }

  boolean isImpossibleToGetRedDiploma() {
    return impossibleToGetRedDiploma;
  }

  boolean canStudentGetRedDiploma() {
    return !impossibleToGetRedDiploma && getMeanGradeAccum().getMean() >= 4.75;
  }
}
