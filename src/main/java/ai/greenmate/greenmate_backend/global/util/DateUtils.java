package ai.greenmate.greenmate_backend.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static LocalDate getLocalDate(String date) {
    return getLocalDate(date,"yyyy-MM-dd");
  }
  public static LocalDate getLocalDate(String date, String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    return LocalDate.parse(date, dateTimeFormatter);
  }

  public static String getDateFromLocalDate(LocalDate localDate, String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return localDate.format(dateTimeFormatter);
  }

  public static String getDateFromLocalDate(LocalDate localDate) {
    return getDateFromLocalDate(localDate, "yyyy-MM-dd");
  }

  public static String getDateFromLocalDateTime(LocalDateTime localDateTime, String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    return localDateTime.format(dateTimeFormatter);
  }
  public static String getDateFromLocalDateTime(LocalDateTime localDateTime) {
    return getDateFromLocalDateTime(localDateTime, "yyyy-MM-dd HH:mm:ss");
  }

  public static LocalDate getLocalDateFromYearMonth(String date, String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    YearMonth yearMonth = YearMonth.parse(date, dateTimeFormatter);
    return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
  }

  public static LocalDateTime getStartOfMonthFromLocalDate(LocalDate localDate) {
    return LocalDateTime.of(
            localDate.getYear(),
            localDate.getMonthValue(),
            1,
            0,
            0,
            0);
  }
  public static LocalDateTime getEndOfMonthFromLocalDate(LocalDate localDate) {
    return LocalDateTime.of(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.lengthOfMonth(),
            23,
            59,
            59);
  }
}
