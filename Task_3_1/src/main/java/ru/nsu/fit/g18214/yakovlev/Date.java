package ru.nsu.fit.g18214.yakovlev;

import ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays;

public class Date {

  private int day;
  private int month;
  private int year;
  private WeekDays dayOfWeek;

  final WeekDays[] associateDayWeekToInt = {WeekDays.Monday,
    WeekDays.Tuesday, WeekDays.Wednesday, WeekDays.Thursday, WeekDays.Friday, WeekDays.Saturday, WeekDays.Sunday,};

  int DayWeekToInt(WeekDays weekDay) {
    int ret = 0;
    switch (weekDay) {
      case Monday:
        ret = 0;
        break;
      case Tuesday:
        ret = 1;
        break;
      case Wednesday:
        ret = 2;
        break;
      case Thursday:
        ret = 3;
        break;
      case Friday:
        ret = 4;
        break;
      case Saturday:
        ret = 5;
        break;
      case Sunday:
        ret = 6;
        break;
    }
    return ret;
  }

  final int[] monthToInt = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public Date(int day, int month, int year, WeekDays dayOfWeek) {
    this.day = day;
    this.month = month;
    this.year = year;
    this.dayOfWeek = dayOfWeek;
  }

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public WeekDays getDayOfWeek() {
    return dayOfWeek;
  }

  void setYear(int year) {
    this.year = year;
  }

  void setMonth(int month) {
    this.month = month;
  }

  int monthToDaysCount() {
    int cnt = monthToInt[month];
    if (isBissextile() && month == 2)
      return 29;
    return cnt;
  }

  int monthToDaysCount(int month) {
    if (isBissextile() && month == 2)
      return 29;
    return monthToInt[month];
  }

  boolean isBissextile() {
    return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
  }

  boolean isBissextile(int year) {
    return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
  }
}
