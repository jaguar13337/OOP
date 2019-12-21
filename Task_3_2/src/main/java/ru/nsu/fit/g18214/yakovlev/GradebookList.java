package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This is a grade book realisation on a List of Sessions. */
public class GradebookList implements Gradebook {

  private ArrayList<Session> gradebook;

  /**
   * Grade book List constructor which is get a List of exams names and List of grades in this
   * exams. String[i] name of exam must have Grades[i] grade.
   *
   * @param grades ArrayList of Integer arrays, which are including a grades in current exam. One
   *     integer array = one session.
   * @throws IllegalArgumentException if you put null as an argument.
   */
  public GradebookList(ArrayList<Grade[]> grades)
      throws IllegalArgumentException {
    if (grades == null) {
      throw new IllegalArgumentException();
    }
    gradebook = new ArrayList<>();
    for (Grade[] gradeArray : grades) {
      Session session = new Session();
      for (Grade grade: gradeArray) {
        session.addExam(grade);
      }
      gradebook.add(session);
    }
  }

  /**
   * This method allow you to add new Session with exams 'namesOfExams' and grades on them 'grades'.
   *
   * @param grades grades on this exams.
   * @throws IllegalArgumentException if you put null as one of element.
   */
  public void addSession(Grade[] grades) throws IllegalArgumentException {
    if (grades == null) {
      throw new IllegalArgumentException();
    }
    Session session = new Session();
    for (Grade grade : grades) {
      session.addExam(grade);
    }
    gradebook.add(session);
  }

  /**
   * This method finds an average score in this gradebook.
   *
   * @return double average score.
   */
  @Override
  public double getAverageScore() {
    double sumOfMeansInSessions = 0;
    for (Session session : gradebook) {
      sumOfMeansInSessions += session.getMeanGradeInThisSession();
    }
    return sumOfMeansInSessions/gradebook.size();
  }

  /**
   * This method understands, can owner os this gradebook get a red diploma.
   *
   * @return returns true, if an owner of gradebook hasn't three grade and his average score more or
   *     equals than 4.75.
   */
  @Override
  public boolean canStudentGetRedDiploma() {
    for (Session session : gradebook) {
      if (session.isHasThree()) {
        return false;
      }
    }
    return getAverageScore() >= 4.75;
  }

  /**
   * This method understands, is an owner of this gradebook getting increased scholarship this
   * semester.
   *
   * @return return true, if an owner of gradebook have average score of last session = 5.
   */
  @Override
  public boolean isStudentGetIncreasedScholarship() {
    Session lastSession = gradebook.get(gradebook.size() - 1);
    return lastSession.getMeanGradeInThisSession() == 5;
  }
}
