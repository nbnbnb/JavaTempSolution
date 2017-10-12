package helper;

import java.time.*;
import java.util.Date;

/**
 * Created by jinzhanga on 2017/10/12.
 * <p>
 * Java 8 中 Date与LocalDateTime、LocalDate、LocalTime互转
 * <p>
 * Java 8中 java.util.Date 类新增了两个方法，分别是from(Instant instant)和toInstant()方法
 * <p>
 * // Obtains an instance of Date from an Instant object.
 * public static Date from(Instant instant) {
 * try {
 * return new Date(instant.toEpochMilli());
 * } catch (ArithmeticException ex) {
 * throw new IllegalArgumentException(ex);
 * }
 * }
 * <p>
 * // Converts this Date object to an Instant.
 * public Instant toInstant() {
 * return Instant.ofEpochMilli(getTime());
 * }
 */
public class DateHelper {
    // 01. java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    // 02. java.util.Date --> java.time.LocalDate
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    // 03. java.util.Date --> java.time.LocalTime
    public static LocalTime dateToLocalTime(Date date) {
        return dateToLocalDateTime(date).toLocalTime();
    }

    // 04. java.time.LocalDateTime --> java.util.Date
    public static Date localDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    // 05. java.time.LocalDate --> java.util.Date
    public static Date localDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}
