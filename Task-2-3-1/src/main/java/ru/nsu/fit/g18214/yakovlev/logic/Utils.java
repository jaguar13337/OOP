package ru.nsu.fit.g18214.yakovlev.logic;

import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Set;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.ControlPoint;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Group;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Lesson;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Task;
import ru.nsu.fit.g18214.yakovlev.git.Github;
import ru.nsu.fit.g18214.yakovlev.git.GithubService;

class Utils {
  private final String[] headers;
  private Config config;

  Utils(String[] headers, Config config) {
    this.headers = headers;
    this.config = config;
  }

  String arrayToTable(String[][] array, int numberOfTasks) {
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

  Group findGroup(Integer groupID) {
    for (Group group : config.getGroups()) {
      if (group.getGroupId().equals(groupID)) {
        return group;
      }
    }
    return null;
  }

  void checkAttendance(Group group) {
    GithubService github = new Github();
    for (Lesson lesson : group.getLessons()) {
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(lesson.getDate());
      calendar.roll(Calendar.DAY_OF_MONTH, 7);
      for (Student student : group.getStudents()) {
        if (!lesson.getAttendance().containsKey(student.getId())) {
          if (github.countCommitsNumber(student.getRepoUrl(), calendar.getTime(), lesson.getDate()) > 1) {
            lesson.getAttendance().put(student.getId(), true);
          } else {
            lesson.getAttendance().put(student.getId(), false);
          }
        }
      }
    }
  }

  int getTasksCount() {
    return config.getTasks().size();
  }

  int getControlsCount() {
    return config.getControls().size();
  }

  int getHeadersLength() {
    return headers.length;
  }

  Set<ControlPoint> getControls() {
    return config.getControls();
  }

  String getHeader(int num) {
    return headers[num];
  }

  Task getTask(int num) {
    return config.getTasks().get(num);
  }
}
