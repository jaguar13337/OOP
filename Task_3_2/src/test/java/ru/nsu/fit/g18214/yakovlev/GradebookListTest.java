package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class GradebookListTest {

  @Test
  public void test() {
    ArrayList<String[]> examsNames = new ArrayList<>();
    String[] f1 = new String[6];
    f1[0] = "Math analysis";
    f1[1] = "Imperative programming";
    f1[2] = "Declarative programming";
    f1[3] = "Discrete math";
    f1[4] = "English";
    f1[5] = "History";
    examsNames.add(f1);
    String[] f2 = new String[6];
    f2[0] = "Math analysis";
    f2[1] = "Imperative programming";
    f2[2] = "Declarative programming";
    f2[3] = "Graph theory";
    f2[4] = "English";
    f2[5] = "Russian";
    examsNames.add(f2);
    ArrayList<Integer[]> grades = new ArrayList<>();
    Integer[] g1 = new Integer[6];
    g1[0] = 5;
    g1[1] = 5;
    g1[2] = 5;
    g1[3] = 5;
    g1[4] = 5;
    g1[5] = 5;
    grades.add(g1);
    Integer[] g2 = new Integer[6];
    g2[0] = 4;
    g2[1] = 5;
    g2[2] = 5;
    g2[3] = 5;
    g2[4] = 4;
    g2[5] = 5;
    grades.add(g2);
    GradebookList gradebook = new GradebookList(examsNames, grades);
    Assert.assertFalse(gradebook.isStudentGetIncreasedScholarship());
    Assert.assertTrue(gradebook.canStudentGetRedDiploma());
    Assert.assertEquals(4.83, gradebook.getAverageScore(), 0.01);
    String[] f3 = new String[6];
    f3[0] = "Math analysis";
    f3[1] = "Imperative programming";
    f3[2] = "Declarative programming";
    f3[3] = "Graph theory";
    f3[4] = "English";
    f3[5] = "Russian";
    Integer[] g3 = new Integer[6];
    g3[0] = 4;
    g3[1] = 4;
    g3[2] = 4;
    g3[3] = 4;
    g3[4] = 4;
    g3[5] = 4;
    gradebook.addSession(f3,g3);
    Assert.assertFalse(gradebook.isStudentGetIncreasedScholarship());
    Assert.assertFalse(gradebook.canStudentGetRedDiploma());
    Assert.assertEquals(4.55, gradebook.getAverageScore(), 0.01);
  }
}