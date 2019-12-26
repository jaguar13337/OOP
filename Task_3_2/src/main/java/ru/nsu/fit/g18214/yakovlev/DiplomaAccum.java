package ru.nsu.fit.g18214.yakovlev;

class DiplomaAccum {
  private MeanGradeAccum meanGradeAccum;
  private boolean hasThree;
  private boolean graduateWorkGradeIsFive;



  DiplomaAccum() {
    meanGradeAccum = new MeanGradeAccum();
    hasThree = false;
    graduateWorkGradeIsFive = false;
  }

  MeanGradeAccum getMeanGradeAccum() {
    return meanGradeAccum;
  }

  void setHasThree(boolean hasThree) {
    this.hasThree = hasThree;
  }


  boolean isHasThree() {
    return hasThree;
  }

  void setGraduateWorkGradeIsFive(boolean graduateWorkGradeIsFive) {
    this.graduateWorkGradeIsFive = graduateWorkGradeIsFive;
  }


  boolean canStudentGetRedDiploma() {
    return !hasThree && getMeanGradeAccum().getMean() >= 4.75 && graduateWorkGradeIsFive;
  }



}

