package basicconsoleapp.helper

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


object DateTimeHelper {

    // yyyy-MM-dd HH:mm:ss
    // yyyy-MM-dd HH:mm:ss.SSS  精确到毫秒（.NET 中是 yyyy-MM-dd HH:mm:ss.fff）

    // 和老的 java.util.DateFormat 相比较，所有的 DateTimeFormat 实例都是线程安全的
    // 所以，此处可以全局使用
    private val localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // 新的 java.time.ZoneId 类是老版 java.util.TimeZone 的替代品
    private val zone = ZoneId.of("Asia/Shanghai")  // 默认东八时区
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
        val tp = Calendar.getInstance()
        tp.time = timestamp
        return tp
    }

    /**
     * 格式化字符串时间为 Timestamp
     *
     * 传入字符串格式为 yyyy-MM-dd hh:ss:mm
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseDateTimeToTimestamp(dateTimeFormat: String) = Timestamp.valueOf(parseToLocalDateTime(dateTimeFormat))

    /**
     * 格式化字符串时间为 Timestamp
     *
     * 传入字符串格式为 yyyy-MM-dd
     *
     * @param dateFormat
     * @return
     */
    fun parseDateToTimestamp(dateFormat: String) =
            Timestamp.valueOf(dateToLocalDateTime(localDateToDate(parseToLocalDate(dateFormat))))

    /**
     * 格式化字符串时间为 LocalDateTime
     *
     * 传入字符串格式为 yyyy-MM-dd hh:ss:mm
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseToLocalDateTime(dateTimeFormat: String) = LocalDateTime.parse(dateTimeFormat, localDateTimeFormatter)

    /**
     * 格式化字符串时间为 LocalDate
     *
     * 传入字符串格式为 yyyy-MM-dd
     *
     * @param dateTimeFormat
     * @return
     */
    fun parseToLocalDate(dateTimeFormat: String) = LocalDate.parse(dateTimeFormat, localDateFormatter)

    /**
     * java.util.Date --> java.time.LocalDateTime
     */
    fun dateToLocalDateTime(date: Date) = LocalDateTime.ofInstant(date.toInstant(), zone)

    /**
     * java.util.Date --> java.time.LocalDate
     */
    fun dateToLocalDate(date: Date) = dateToLocalDateTime(date).toLocalDate()

    /**
     * java.util.Date --> java.time.LocalTime
     */
    fun dateToLocalTime(date: Date) = dateToLocalDateTime(date).toLocalTime()

    /**
     * java.time.LocalDateTime --> java.util.Date
     */
    fun localDateTimeToDate(localDateTime: LocalDateTime) = Date.from(localDateTime.atZone(zone).toInstant())

    /**
     * java.time.LocalDate --> java.util.Date
     */
    fun localDateToDate(localDate: LocalDate) = Date.from(localDate.atStartOfDay().atZone(zone).toInstant())

    /**
     * java.sql.Timestamp --> java.time.LocalDateTime
     */
    fun timestampToLocalDateTime(timeStamp: Timestamp) = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp.time), zone)

    /**
     * 将 Timestamp 按照自定义格式化
     */
    fun timeStampToCustomString(timestamp: Timestamp, format: String) =
            timestampToLocalDateTime(timestamp).format(DateTimeFormatter.ofPattern(format))

    /**
     * 将 Date 转换为指定格式的字符串
     *
     * @param format 默认格式为 yyyy-MM-dd HH:mm:ss
     */
    fun dateToString(date: Date, format: String = "yyyy-MM-dd HH:mm:ss") = SimpleDateFormat(format).format(date)
}
