package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;

/** This is a grade book realisation on a List of Sessions */
public class GradebookList implements Gradebook {

  private ArrayList<Session> gradebook;

  /**
   * Grade book List constructor which is get a List of exams names and List of grades in this
   * exams. String[i] name of exam must have Grades[i] grade.
   *
   * @param namesOfExams ArrayList of String arrays, which are including a names of exams. One
   *     string array = one session.
   * @param grades ArrayList of Integer arrays, which are including a grades in current exam. One
   *     integer array = one session.
   */
  public GradebookList(ArrayList<String[]> namesOfExams, ArrayList<Integer[]> grades) {
    if (namesOfExams.size() != grades.size()) throw new IllegalArgumentException();
    gradebook = new ArrayList<>();
    for (int i = 0; i < namesOfExams.size(); i++)
      gradebook.add(new Session(namesOfExams.get(i), grades.get(i)));
  }

  public void addSession(String[] namesOfExams, Integer[] grades) {
    gradebook.add(new Session(namesOfExams, grades));
  }

  /**
   * This method finds an average score in this gradebook.
   *
   * @return double average score.
   */
  @Override
  public double getAverageScore() {
    double averageScore = 0;
    for (Session session : gradebook) averageScore += session.getAverageGradeInThisSession();
    averageScore /= gradebook.size();
    return averageScore;
  }

  /**
   * This method understands, can owner os this gradebook get a red diploma.
   *
   * @return returns true, if an owner of gradebook hasn't three grade and his average score more or
   *     equals than 4.75.
   */
  @Override
  public boolean canStudentGetRedDiploma() {
    for (Session session : gradebook) if (session.isHasThree()) return false;
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
    return lastSession.getAverageGradeInThisSession() == 5;
  }
}
