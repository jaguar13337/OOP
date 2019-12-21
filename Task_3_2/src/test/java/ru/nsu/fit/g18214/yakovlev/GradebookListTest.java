package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class GradebookListTest {

  @Test
  public void test() {
    ArrayList<Grade[]> exams = new ArrayList<>();
    Grade[] f1 = new Grade[7];
    f1[0] = new GradeFive("Math analysis", 5);
    f1[1] = new GradeFive("Imperative programming", 5);
    f1[2] = new GradeFive("Declarative programming", 5);
    f1[3] = new GradeFive("Discrete math", 5);
    f1[4] = new Credit("English", true);
    f1[5] = new GradeFive("History", 5);
    f1[6] = new Credit("Sport", true);
    exams.add(f1);
    Grade[] f2 = new Grade[7];
    f2[0] = new GradeFive("Math analysis", 4);
    f2[1] = new GradeFive("Imperative programming", 5);
    f2[2] = new GradeFive("Declarative programming", 5);
    f2[3] = new GradeFive("Graph theory", 5);
    f2[4] = new GradeFive("English", 4);
    f2[5] = new GradeFive("Russian",5);
    f2[6] = new Credit("Sport", true);
    exams.add(f2);
    GradebookList gradebook = new GradebookList(exams);
    Assert.assertFalse(gradebook.isStudentGetIncreasedScholarship());
    Assert.assertTrue(gradebook.canStudentGetRedDiploma());
    Assert.assertEquals(4.83, gradebook.getAverageScore(), 0.01);
    Grade[] f3 = new Grade[6];
    f3[0] = new GradeFive("Math analysis", 4);
    f3[1] = new GradeFive("Imperative programming", 4);
    f3[2] = new GradeFive("Declarative programming", 4);
    f3[3] = new GradeFive("Graph theory", 4);
    f3[4] = new GradeFive("English", 4);
    f3[5] = new GradeFive("Russian", 4);
    gradebook.addSession(f3);
    Assert.assertFalse(gradebook.isStudentGetIncreasedScholarship());
    Assert.assertFalse(gradebook.canStudentGetRedDiploma());
    Assert.assertEquals(4.55, gradebook.getAverageScore(), 0.01);
  }
}
