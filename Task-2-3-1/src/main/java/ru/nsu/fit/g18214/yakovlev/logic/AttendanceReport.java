package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Group;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Lesson;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Student;

public class AttendanceReport implements Command {

  private Integer groupID;
  private Writer writer;
  private Utils utils;

  AttendanceReport(Integer groupID, Writer writer, Utils utils) {
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

    for (Lesson lesson : group.getLessons()) {
      tableData[0][i] = formatter.format(lesson.getDate());
      int j = 1;
      for (Student student : group.getStudents()) {
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
    writer.write(utils.arrayToTable(tableData, 0));
    writer.write("<br><br><br>");
  }
}
