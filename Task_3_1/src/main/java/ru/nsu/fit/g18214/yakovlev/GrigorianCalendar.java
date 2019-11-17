package ru.nsu.fit.g18214.yakovlev;

import static java.lang.StrictMath.abs;

public class GrigorianCalendar {

  public enum WeekDays {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday
  }

  private Date date;

  public GrigorianCalendar(int day, int month, int year, WeekDays dayOfWeek) {
    this.date = new Date(day, month, year, dayOfWeek);
  }

  public WeekDays weekDayAfterNDays(int days) {
    int numCurr = date.DayWeekToInt(date.getDayOfWeek());
    int diff = abs(date.getDay() % 7 - numCurr);
    int last = (date.getDay() + days + diff) % 7;
    return date.associateDayWeekToInt[last];
  }

  public String howMuchTimeFromGivenDay(int dayG, int monthG, int yearG) {
    int day = date.getDay() - dayG;
    int month = date.getMonth();
    int year = date.getYear();
    if (day < 0) {
      day += date.monthToDaysCount();
      month--;
    }
    month -= monthG;
    if (month < 0) {
      month += 12;
      year--;
    }
    year -= yearG;
    return (year + " years " + month +
      " months " + day + " days have passed since the given date");
  }

  private int getMonthCode(int month) {
    int ret = -1;
    switch (month) {
      case 1:
      case 10:
        ret = 1;
        break;
      case 5:
        ret = 2;
        break;
      case 8:
        ret = 3;
        break;
      case 2:
      case 3:
      case 11:
        ret = 4;
        break;
      case 6:
        ret = 5;
        break;
      case 12:
      case 9:
        ret = 6;
        break;
      case 4:
      case 7:
        ret = 0;
        break;
    }
    return ret;
  }

  private int getYearCode(int year) {
    int firstNum = year / 100 % 4;
    switch (firstNum) {
      case 0:
        firstNum = 6;
        break;
      case 1:
        firstNum = 4;
        break;
      case 2:
        firstNum = 2;
        break;
      case 3:
        firstNum = 0;
        break;
    }
    return (firstNum + year % 100 + year % 100 / 4) % 7;
  }

  public WeekDays weekDayOfGivenDay(int day, int month, int year) {
    int monthCode = getMonthCode(month);
    int yearCode = getYearCode(year);
    if (date.isBissextile(year) && (month == 2 || month == 1))
      yearCode--;
    return date.associateDayWeekToInt[(day + monthCode + yearCode - 2) % 7];
  }

  public int whichMonthAfterNWeeks(int weeksCount) {
    int days = weeksCount * 7;
    Date date = new Date(this.date.getDay(), this.date.getMonth(), this.date.getYear(), this.date.getDayOfWeek());
    while (days + date.getDay() > date.monthToDaysCount()) {
      days -= date.monthToDaysCount();
      date.setMonth(date.getMonth() + 1);
      if (date.getMonth() > 12) {
        date.setMonth(1);
        date.setYear(date.getYear() + 1);
      }
    }
    return date.getMonth();
  }

  public int daysUntilGivenDate(int day, int month, int year) {
    int days = date.monthToDaysCount() - date.getDay() + day;
    int diffmonths = month - date.getMonth() + 12 * (year - date.getYear());
    int start = date.getMonth() + 1;
    Date newDate = new Date(1, 1, date.getYear(), null);
    for (int i = start; i < start + diffmonths - 1; i++) {
      if (i > 12) {
        i = 1;
        start = 0;
        newDate.setYear(newDate.getYear() + 1);
      }
      days += newDate.monthToDaysCount(i);
    }
    return days;
  }

  public Date nearestWeekDayWithGivenDayNumber(WeekDays weekDay, int day) {
    int month = date.getMonth();
    int year = date.getYear();
    if (day < date.getDay())
      month++;
    if (month > 12) {
      month = 1;
      year++;
    }
    while (weekDayOfGivenDay(day, month, year) != weekDay) {
      month++;
      if (month > 12) {
        month = 1;
        year++;
      }
    }
    return new Date(day, month, year, weekDay);
  }
}
