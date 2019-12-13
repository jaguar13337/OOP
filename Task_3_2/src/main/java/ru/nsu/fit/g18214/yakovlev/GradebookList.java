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
   * @param namesOfExams ArrayList of String arrays, which are including a names of exams. One
   *     string array = one session.
   * @param grades ArrayList of Integer arrays, which are including a grades in current exam. One
   *     integer array = one session.
   * @throws IllegalArgumentException if count of exams not equals to count of grades, or if you put
   *     null as an argument.
   */
  public GradebookList(ArrayList<String[]> namesOfExams, ArrayList<Integer[]> grades)
      throws IllegalArgumentException {
    if (namesOfExams == null || grades == null || namesOfExams.size() != grades.size()) {
      throw new IllegalArgumentException();
    }
    gradebook = new ArrayList<>();
    for (int i = 0; i < namesOfExams.size(); i++) {
      gradebook.add(new Session(namesOfExams.get(i), grades.get(i)));
    }
  }

  /**
   * This method allow you to add new Session with exams 'namesOfExams' and grades on them 'grades'.
   *
   * @param namesOfExams names of passed exams.
   * @param grades grades on this exams.
   * @throws IllegalArgumentException if you put null as one of element.
   */
  public void addSession(String[] namesOfExams, Integer[] grades) throws IllegalArgumentException {
    if (namesOfExams == null || grades == null) {
      throw new IllegalArgumentException();
    }
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
    for (Session session : gradebook) {
      averageScore += session.getAverageGradeInThisSession();
    }
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
    for (Session session : gradebook) {
      if (session.isHasThree()) {
        return false;
      }
    }
    return getDiplomaAverageScore() >= 4.75;
  }

  private double getDiplomaAverageScore() {
    Map<String, Integer> table = new HashMap<>();
    for (Session session : gradebook) {
      for (Exam exam : session.getSession()) {
        table.put(exam.getNameOfExam(), exam.getGrade());
      }
    }
    List<String> keys = new ArrayList<>(table.keySet());
    double ans = 0;
    for (String str : keys) ans += table.get(str);
    return ans / keys.size();
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
