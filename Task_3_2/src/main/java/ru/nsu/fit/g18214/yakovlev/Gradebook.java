package ru.nsu.fit.g18214.yakovlev;

public interface Gradebook {

  /**
   * This method finds an average score among all exams in current gradebook.
   *
   * @return Average score in all exams in this gradebook.
   */
  double getAverageScore();

  /**
   * This method determines is an owner of this gradebook can receive a red diploma or not.
   *
   * @return True, if student can receive a red diploma and false otherwise.
   */
  boolean canStudentGetRedDiploma();

  /**
   * This method defines by last session, if an owner of this gradebook will receive increased
   * scholarship.
   *
   * @return True, if student will receive increased scholarship and false otherwise.
   */
  boolean isStudentGetIncreasedScholarship();
}
