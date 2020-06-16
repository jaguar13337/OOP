package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.AcceptedTaskDSL;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.ConfigDSL;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.ControlPointDSL;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.GroupDSL;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.LessonDSL;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.StudentDSL;
import ru.nsu.fit.g18214.yakovlev.git.GithubCalls;
import ru.nsu.fit.g18214.yakovlev.git.GithubPlug;
import ru.nsu.fit.g18214.yakovlev.gradle.GradleCalls;
import ru.nsu.fit.g18214.yakovlev.gradle.GradlePlug;

public class Logic {

  private final String[] headers = {"Build", "Style", "Doc", "Tests", "Credit"};
  private ConfigDSL config;
  private boolean attendanceChecked = false;

  public Logic(ConfigDSL config) {
    this.config = config;
  }

  private void checkAttendance(GroupDSL group) {
    GithubCalls github = new GithubPlug();
    for (LessonDSL lesson : group.getLessons()) {
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(lesson.getDate());
      calendar.roll(Calendar.DAY_OF_MONTH, 7);
      for (StudentDSL student : group.getStudents()) {
        if (!lesson.getAttendance().containsKey(student.getId())) {
          if (github.countCommitsNumber(student.getRepoUrl(), calendar.getTime(), lesson.getDate()) > 1) {
            lesson.getAttendance().put(student.getId(), true);
          } else {
            lesson.getAttendance().put(student.getId(), false);
          }
        }
      }
    }
    attendanceChecked = true;
  }

  public void attendanceReport(Integer groupID, FileWriter writer)
    throws IllegalArgumentException, IOException {
    GroupDSL group = findGroup(groupID);

    if (group == null) {
      throw new IllegalArgumentException();
    }

    if (!attendanceChecked) {
      checkAttendance(group);
    }

    String[][] tableData = new String
      [group.getStudents().size() + 1]
      [group.getLessons().size() + 2];

    for (int i = 0; i < tableData.length; i++) {
      for (int j = 0; j < tableData[i].length; j++) {
        tableData[i][j] = "";
      }
    }

    int i = 1;

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");
    int[] attendanceForStudent = new int[group.getStudents().size()];

    for (LessonDSL lesson : group.getLessons()) {
      tableData[0][i] = formatter.format(lesson.getDate());
      int j = 1;
      for (StudentDSL student : group.getStudents()) {
        tableData[j][0] = student.getName();
        tableData[j][i] = lesson.getAttendance().get(student.getId()) ? "+" : "-";
        if (tableData[j][i].equals("+")) {
          attendanceForStudent[j - 1]++;
        }
        j++;
      }
      i++;
    }

    //Total
    tableData[0][i] = "Total";
    for (int j = 1; j < tableData.length; j++) {
      tableData[j][i] =
        Math.round((double) attendanceForStudent[j - 1] / group.getLessons().size() * 100) + "%";
    }

    writer.write("Посещаемость группы: " + groupID);
    writer.write("<br><br><br>");
    writer.write(arrayToTable(tableData, 0));
    writer.write("<br><br><br>");
  }

  public void performanceReport(Integer groupID, FileWriter writer)
    throws IllegalArgumentException, IOException {

    GroupDSL group = findGroup(groupID);

    if (group == null) {
      throw new IllegalArgumentException();
    }
    if (!attendanceChecked) {
      checkAttendance(group);
    }
    String[][] tableData = new String
      [2 + group.getStudents().size()]
      [config.getTasks().size() * headers.length + config.getControls().size() + 2];

    for (int i = 0; i < tableData.length; i++) {
      for (int j = 0; j < tableData[i].length; j++) {
        tableData[i][j] = "";
      }
    }

    for (int i = 1; i <= config.getTasks().size(); i++) {
      tableData[0][i] = config.getTasks().get(i - 1).getName();
    }

    for (int i = config.getTasks().size() + 1;
         i < config.getTasks().size() + config.getControls().size() + 1;
         i++) {
      tableData[0][i] = "Control Point " + (i - config.getTasks().size());
    }

    tableData[0][config.getTasks().size() + config.getControls().size() + 1] = "Total";
    for (int i = config.getTasks().size() + config.getControls().size() + 2;
         i < tableData[0].length;
         i++) {
      tableData[0][i] = null;
    }

    String[] headerPlusEmptyFields = new String
      [headers.length * config.getTasks().size() + config.getControls().size() + 2];

    headerPlusEmptyFields[0] = "";

    for (int i = 1; i < headerPlusEmptyFields.length; i++) {
      headerPlusEmptyFields[i] = i <= headers.length * config.getTasks().size()
        ? headers[(i - 1)
        % headers.length] : "";
    }
    tableData[1] = headerPlusEmptyFields;


    for (int i = 0; i < group.getStudents().size(); i++) {
      tableData[i + 2] = fillInfoForStudent(group.getStudents().get(i), group);
    }

    writer.write("Успеваемость студентов группы " + groupID);
    writer.write("<br><br><br>");
    writer.write(arrayToTable(tableData, config.getTasks().size()));
    writer.write("<br><br><br>");
  }

  private String[] fillInfoForStudent(StudentDSL student, GroupDSL group) {
    GradleCalls gradleCalls = new GradlePlug();
    String[] studentData = new String[config.getTasks().size() * headers.length
      + 2 // one for name and the second one - total result
      + config.getControls().size()];
    studentData[0] = student.getName();
    int j = 1;

    //Filling information for the every accepted task.
    for (String taskName : student.getAcceptedTasks().keySet()) {
      studentData[j] =
        gradleCalls.compileTask(taskName, student)
          ? "+"
          : "-";
      j++;

      studentData[j] =
        gradleCalls.checkCodeStyle(taskName, student)
          ? "+"
          : "-";
      j++;

      studentData[j] =
        gradleCalls.generateDocumentation(taskName, student)
          ? "+"
          : "-";
      j++;

      Formatter formatter = new Formatter();
      int[] testsResults = gradleCalls.runTests(taskName, student);
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
    if (j <= headers.length * config.getTasks().size()) {
      j = headers.length * config.getTasks().size() + 1;
    }

    //Filling information for the control points.
    for (ControlPointDSL controlPoint : config.getControls()) {
      int pointsUntilControlPoint = 0;
      for (String taskName : student.getAcceptedTasks().keySet()) {
        AcceptedTaskDSL acceptedTask = student.getAcceptedTasks().get(taskName);
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
    for (LessonDSL lesson : group.getLessons()) {
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
      String.valueOf(Math.ceil(sumOfControlPointsGrades / config.getControls().size()) - fine);
    return studentData;
  }

  private String arrayToTable(String[][] array, int numberOfTasks) {
    StringBuilder html = new StringBuilder(
      "<table style=\"width:100%\">\n" +
        "<style>  \n" +
        "table, th, td {  \n" +
        "  border: 1px solid black;  \n" +
        "  border-collapse: collapse;  \n" +
        "}  \n" +
        "th, td {  \n" +
        "  padding: 5px;  \n" +
        "}  \n" +
        "</style> " +
        "<tr>");
    int j = 0;
    for (String elem : array[0]) {
      if (elem == null) {
        break;
      }
      if (j > 0 && j <= numberOfTasks) {
        Formatter formatter = new Formatter();
        formatter.format("<th colspan=\"%d\">", headers.length);
        html
          .append(formatter)
          .append(elem)
          .append("</th>");
      } else {
        html.append("<th>").append(elem).append("</th>");
      }
      j++;
    }
    html.append("</tr>");
    for (int i = 1; i < array.length; i++) {
      String[] row = array[i];
      html.append("<tr>");
      for (String elem : row) {
        if (elem == null) {
          elem = "";
        }
        html.append("<td>").append(elem).append("</td>");
      }
      html.append("</tr>");
    }
    html.append("</table>");
    return html.toString();
  }

  private GroupDSL findGroup(Integer groupID) {
    for (GroupDSL groupDSL : config.getGroups()) {
      if (groupDSL.getGroupId().equals(groupID)) {
        return groupDSL;
      }
    }
    return null;
  }
}
