import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Friday;
import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Monday;
import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Sunday;
import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Thursday;
import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Tuesday;
import static ru.nsu.fit.g18214.yakovlev.GrigorianCalendar.WeekDays.Wednesday;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.GrigorianCalendar;

public class CalendarTests {
  @Test
  public void test1() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals(Tuesday, calendar.weekDayAfterNDays(1024));
    Assert.assertEquals(Thursday, calendar.weekDayAfterNDays(228));
    Assert.assertEquals(Thursday, calendar.weekDayAfterNDays(1488));
    Assert.assertEquals(Sunday, calendar.weekDayAfterNDays(322));
  }

  @Test
  public void test2() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals("74 years 6 months 8 days have passed since the given date",
      calendar.howMuchTimeFromGivenDay(9, 5, 1945));
    Assert.assertEquals("119 years 10 months 16 days have passed since the given date",
      calendar.howMuchTimeFromGivenDay(1, 1, 1900));
    Assert.assertEquals("219 years 0 months 0 days have passed since the given date",
      calendar.howMuchTimeFromGivenDay(17, 11, 1800));
  }

  @Test
  public void test3() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals(Wednesday, calendar.weekDayOfGivenDay(8, 11, 2000));
    Assert.assertEquals(Friday, calendar.weekDayOfGivenDay(8, 11, 1652));
    Assert.assertEquals(Monday, calendar.weekDayOfGivenDay(31, 12, 2232));
  }

  @Test
  public void test4() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals(3, calendar.whichMonthAfterNWeeks(17));
    Assert.assertEquals(11, calendar.whichMonthAfterNWeeks(50));
    Assert.assertEquals(6, calendar.whichMonthAfterNWeeks(32));
  }

  @Test
  public void test5() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals(45, calendar.daysUntilGivenDate(1, 1, 2020));
    Assert.assertEquals(357, calendar.daysUntilGivenDate(8, 11, 2020));
    Assert.assertEquals(335, calendar.daysUntilGivenDate(17, 10, 2020));
    Assert.assertEquals(195, calendar.daysUntilGivenDate(30, 5, 2020));
  }

  @Test
  public void test6() {
    GrigorianCalendar calendar = new GrigorianCalendar(17, 11, 2019, Sunday);
    Assert.assertEquals(12, calendar.nearestWeekDayWithGivenDayNumber(Friday, 13).getMonth());
    Assert.assertEquals(11, calendar.nearestWeekDayWithGivenDayNumber(Sunday, 17).getMonth());
    Assert.assertEquals(1, calendar.nearestWeekDayWithGivenDayNumber(Wednesday, 1).getMonth());
  }
}
