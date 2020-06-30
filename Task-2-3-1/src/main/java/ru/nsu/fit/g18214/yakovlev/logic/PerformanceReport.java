package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;
import java.util.Formatter;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.AcceptedTask;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.ControlPoint;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Group;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Lesson;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.gradle.Gradle;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleService;

public class PerformanceReport implements Command {

  private Integer groupID;
  private Writer writer;
  private Utils utils;

  PerformanceReport(Integer groupID, Writer writer, Utils utils) {
    this.groupID = groupID;
    this.writer = writer;
    this.utils = utils;
  }

  @Override
  public void runCommand() throws IOException {

    Group group = utils.findGroup(groupID);

    if (group == null) {
      throw new IllegalArgumentException();
    }
    utils.checkAttendance(group);
    String[][] tableData = new String
      [2 + group.getStudents().size()]
      [utils.getTasksCount()
      * utils.getHeadersLength()
      + utils.getControlsCount()
      + 2];

    for (int i = 0; i < tableData.length; i++) {
      for (int j = 0; j < tableData[i].length; j++) {
        tableData[i][j] = "";
      }
    }

    for (int i = 1; i <= utils.getTasksCount(); i++) {
      tableData[0][i] = utils.getTask(i - 1).getName();
    }

    for (int i = utils.getTasksCount() + 1;
         i < utils.getTasksCount() + utils.getControlsCount() + 1;
         i++) {
      tableData[0][i] = "Control Point " + (i - utils.getTasksCount());
    }

    tableData[0][utils.getTasksCount() + utils.getControlsCount() + 1] = "Total";
    for (int i = utils.getTasksCount() + utils.getControlsCount() + 2;
         i < tableData[0].length;
         i++) {
      tableData[0][i] = null;
    }

    String[] headerPlusEmptyFields = new String
      [utils.getHeadersLength()
      * utils.getTasksCount()
      + utils.getControlsCount()
      + 2];

    headerPlusEmptyFields[0] = "";

    for (int i = 1; i < headerPlusEmptyFields.length; i++) {
      headerPlusEmptyFields[i] = i <= utils.getHeadersLength() * utils.getTasksCount()
        ? utils.getHeader((i - 1) % utils.getHeadersLength())
        : "";
    }
    tableData[1] = headerPlusEmptyFields;


    for (int i = 0; i < group.getStudents().size(); i++) {
      tableData[i + 2] = fillInfoForStudent(group.getStudents().get(i), group);
    }

    writer.write("Успеваемость студентов группы " + groupID);
    writer.write("<br><br><br>");
    writer.write(utils.arrayToTable(tableData, utils.getTasksCount()));
    writer.write("<br><br><br>");
  }


  private String[] fillInfoForStudent(Student student, Group group) {
    GradleService gradleService = new Gradle();
    String[] studentData = new String[utils.getTasksCount() * utils.getHeadersLength()
      + 2 // one for name and the second one - total result
      + utils.getControlsCount()];
    studentData[0] = student.getName();
    int j = 1;

    //Filling information for the every accepted task.
    for (String taskName : student.getAcceptedTasks().keySet()) {
      studentData[j] =
        gradleService.compileTask(taskName, student)
          ? "+"
          : "-";
      j++;

      studentData[j] =
        gradleService.checkCodeStyle(taskName, student)
          ? "+"
          : "-";
      j++;

      studentData[j] =
        gradleService.generateDocumentation(taskName, student)
          ? "+"
          : "-";
      j++;

      Formatter formatter = new Formatter();
      int[] testsResults = gradleService.runTests(taskName, student);
      formatter.format("%d/%d/%d", testsResults[0], testsResults[1], testsResults[2]);
      studentData[j] = formatter.toString();
      j++;

      studentData[j] = student.getAcceptedTasks()
        .get(taskName)
        .getPointForTask()
        .toString();

      j++;
    }
    double sumOfControlPointsGrades = 0;
    if (j <= utils.getHeadersLength() * utils.getTasksCount()) {
      j = utils.getHeadersLength() * utils.getTasksCount() + 1;
    }

    //Filling information for the control points.
    for (ControlPoint controlPoint : utils.getControls()) {
      int pointsUntilControlPoint = 0;
      for (String taskName : student.getAcceptedTasks().keySet()) {
        AcceptedTask acceptedTask = student.getAcceptedTasks().get(taskName);
        if (acceptedTask.getDateWhenWasAccepted().compareTo(controlPoint.getDate()) <= 0) {
          pointsUntilControlPoint += acceptedTask.getPointForTask();
        } else {
          break;
        }
      }
      int grade = 2;
      if (pointsUntilControlPoint >= controlPoint.getPointsForExcGrade()) {
        grade = 5;
      } else if (pointsUntilControlPoint >= controlPoint.getPointsForGoodGrade()) {
        grade = 4;
      } else if (pointsUntilControlPoint >= controlPoint.getPointsForSatGrade()) {
        grade = 3;
      }
      Formatter formatter = new Formatter();
      formatter.format("%d/%d", pointsUntilControlPoint, grade);
      studentData[j++] = formatter.toString();
      sumOfControlPointsGrades += grade;
    }

    int attendance = 0;
    for (Lesson lesson : group.getLessons()) {
      if (lesson.getAttendance().get(student.getId())) {
        attendance++;
      }
    }

    int fine = 0;
    if ((double) attendance / group.getLessons().size() * 100 < 50) {
      fine = 2;
    } else if ((double) attendance / group.getLessons().size() * 100 < 75) {
      fine = 1;
    }

    studentData[j] =
      String.valueOf(Math.ceil(sumOfControlPointsGrades / utils.getControlsCount()) - fine);
    return studentData;
  }
}
