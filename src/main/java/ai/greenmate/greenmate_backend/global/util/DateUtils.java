package ai.greenmate.greenmate_backend.global.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static LocalDate getLocalDate(String date) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(date, dateTimeFormatter);
  }

  public static String getDateFromLocalDate(LocalDate localDate, String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return localDate.format(dateTimeFormatter);
  }

  public static String getDateFromLocalDate(LocalDate localDate) {
    return getDateFromLocalDate(localDate, "yyyy-MM-dd");
  }
}
