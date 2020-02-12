package ru.nsu.fit.g18214.yakovlev;

class LoggingInStdout implements Log {

  public LoggingInStdout() {}

  /**
   * Prints log message in stdout
   *
   * @param message which you want to print
   */
  @Override
  public void logGivenMessage(String message) {
    System.out.println(message);
  }
}
