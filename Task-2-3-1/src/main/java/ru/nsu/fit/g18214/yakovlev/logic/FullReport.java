package ru.nsu.fit.g18214.yakovlev.logic;

import java.io.IOException;
import java.io.Writer;

public class FullReport implements Command {

  private Integer groupID;
  private Writer writer;
  private Utils utils;

  FullReport(Integer groupID, Writer writer, Utils utils) {
    this.groupID = groupID;
    this.writer = writer;
    this.utils = utils;
  }

  @Override
  public void runCommand() throws IOException {
    new PerformanceReport(groupID, writer, utils).runCommand();
    new AttendanceReport(groupID, writer, utils).runCommand();
  }
}
