package helper

import java.time.*
import java.util.Date

/**
 * Created by jinzhanga on 2017/10/12.
 *
 *
 * Java 8 中 Date与LocalDateTime、LocalDate、LocalTime互转
 *
 *
 * Java 8中 java.util.Date 类新增了两个方法，分别是from(Instant instant)和toInstant()方法
 *
 *
 * // Obtains an instance of Date from an Instant object.
 * public static Date from(Instant instant) {
 * try {
 * return new Date(instant.toEpochMilli());
 * } catch (ArithmeticException ex) {
 * throw new IllegalArgumentException(ex);
 * }
 * }
 *
 *
 * // Converts this Date object to an Instant.
 * public Instant toInstant() {
 * return Instant.ofEpochMilli(getTime());
 * }
 */
object DateHelper {
    // 01. java.util.Date --> java.time.LocalDateTime
    fun dateToLocalDateTime(date: Date): LocalDateTime {
        val instant = date.toInstant()
        val zone = ZoneId.systemDefault()
        return LocalDateTime.ofInstant(instant, zone)
    }

    // 02. java.util.Date --> java.time.LocalDate
    fun dateToLocalDate(date: Date): LocalDate {
        return dateToLocalDateTime(date).toLocalDate()
    }

    // 03. java.util.Date --> java.time.LocalTime
    fun dateToLocalTime(date: Date): LocalTime {
        return dateToLocalDateTime(date).toLocalTime()
    }

    // 04. java.time.LocalDateTime --> java.util.Date
    fun localDateTimeToUdate(localDateTime: LocalDateTime): Date {
        val zone = ZoneId.systemDefault()
        val instant = localDateTime.atZone(zone).toInstant()
        return Date.from(instant)
    }

    // 05. java.time.LocalDate --> java.util.Date
    fun localDateToUdate(localDate: LocalDate): Date {
        val zone = ZoneId.systemDefault()
        val instant = localDate.atStartOfDay().atZone(zone).toInstant()
        return Date.from(instant)
    }
}
