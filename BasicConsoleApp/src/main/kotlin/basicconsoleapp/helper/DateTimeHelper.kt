package basicconsoleapp.helper

import java.sql.Timestamp
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeHelper {

    // yyyy-MM-dd HH:mm:ss
    // yyyy-MM-dd HH:mm:ss.SSS  精确到毫秒（.NET 中是 yyyy-MM-dd HH:mm:ss.fff）

    private val localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    /**
     * 获取当前时间 Timestamp
     *
     * @return
     */
    val currentTimestamp: Timestamp
        get() = Timestamp(System.currentTimeMillis())

    /**
     * 获取当前时间 Calendar
     *
     * @return
     */
    val currentCalendar: Calendar
        get() = Calendar.getInstance()

    /**
     * Timestamp 转 Calendar
     *
     * @param timestamp
     * @return
     */
    fun toCalendar(timestamp: Timestamp): Calendar {
        currentCalendar.time = timestamp
        return currentCalendar
    }

    /**
     * 格式化字符串时间为 Timestamp
     *
     * 传入字符串格式为 yyyy-MM-dd hh:ss:mm
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseDateTimeToTimestamp(dateTimeFormat: String): Timestamp = Timestamp.valueOf(parseToLocalDateTime(dateTimeFormat))

    /**
     * 格式化字符串时间为 Timestamp
     *
     * 传入字符串格式为 yyyy-MM-dd
     *
     * @param dateFormat
     * @return
     */
    fun parseDateToTimestamp(dateFormat: String): Timestamp =
            Timestamp.valueOf(dateToLocalDateTime(localDateToDate(parseToLocalDate(dateFormat))))

    /**
     * 格式化字符串时间为 LocalDateTime
     *
     * 传入字符串格式为 yyyy-MM-dd hh:ss:mm
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseToLocalDateTime(dateTimeFormat: String): LocalDateTime =
            LocalDateTime.parse(dateTimeFormat, localDateTimeFormatter)

    /**
     * 格式化字符串时间为 LocalDate
     *
     * 传入字符串格式为 yyyy-MM-dd
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseToLocalDate(dateTimeFormat: String): LocalDate = LocalDate.parse(dateTimeFormat, localDateFormatter)

    /**
     * java.util.Date --> java.time.LocalDateTime
     */
    fun dateToLocalDateTime(date: Date): LocalDateTime {
        val instant = date.toInstant()
        val zone = ZoneId.systemDefault()
        return LocalDateTime.ofInstant(instant, zone)
    }

    /**
     * java.util.Date --> java.time.LocalDate
     */
    fun dateToLocalDate(date: Date): LocalDate = dateToLocalDateTime(date).toLocalDate()

    /**
     * java.util.Date --> java.time.LocalTime
     */
    fun dateToLocalTime(date: Date): LocalTime = dateToLocalDateTime(date).toLocalTime()

    /**
     * java.time.LocalDateTime --> java.util.Date
     */
    fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        val zone = ZoneId.systemDefault()
        val instant = localDateTime.atZone(zone).toInstant()
        return Date.from(instant)
    }

    /**
     * java.time.LocalDate --> java.util.Date
     */
    fun localDateToDate(localDate: LocalDate): Date {
        val zone = ZoneId.systemDefault()
        val instant = localDate.atStartOfDay().atZone(zone).toInstant()
        return Date.from(instant)
    }

    /**
     * java.sql.Timestamp --> java.time.LocalDateTime
     */
    fun timeStampToLocalDateTime(timeStamp: Timestamp): LocalDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp.time), TimeZone.getDefault().toZoneId())

    /**
     * 将 Timestamp 按照自定义格式化
     */
    fun timeStampToCustomString(timestamp: Timestamp, format: String): String {
        val localDateTime = timeStampToLocalDateTime(timestamp)
        return localDateTime.format(DateTimeFormatter.ofPattern(format))
    }
}
