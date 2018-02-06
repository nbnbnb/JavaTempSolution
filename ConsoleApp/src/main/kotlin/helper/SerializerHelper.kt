package helper

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer

import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by jinzhanga on 2017/10/12.
 */
object SerializerHelper {

    private val mapper = ObjectMapper()
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    init {
        /**
         * 默认输出，时间为 1507790279021 = 2017-10-12T14:37:59.021 = 1970-01-01T00:00:00.000+0000 至当前的毫秒数
         *
         * {
         * "A": 1507790279021,
         * "B": 1507790279021,
         * "C": {
         * "year": 2017,
         * "month": "OCTOBER",
         * "dayOfMonth": 12,
         * "dayOfWeek": "THURSDAY",
         * "era": "CE",
         * "dayOfYear": 285,
         * "leapYear": false,
         * "monthValue": 10,
         * "chronology": {
         * "id": "ISO",
         * "calendarType": "iso8601"
         * }
         * },
         * "D": {
         * "dayOfMonth": 12,
         * "dayOfWeek": "THURSDAY",
         * "month": "OCTOBER",
         * "year": 2017,
         * "dayOfYear": 285,
         * "monthValue": 10,
         * "nano": 21000000,
         * "hour": 14,
         * "minute": 37,
         * "second": 59,
         * "chronology": {
         * "id": "ISO",
         * "calendarType": "iso8601"
         * }
         * }
         * }
         *
         */

        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
        mapper.registerModule(javaTimeModule)
        // 添加 Module 后输出
        // {"A":1507790279021,"B":1507790279021,"C":[2017,10,12],"D":[2017,10,12,14,37,59,21000000]}
        // 对 Date 和 Calendar 没影响，还是为 TimeStamp 形式
        // 对 LocalDate 和 LocalDateTime 有影响，输出为简化版本

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        // configure 后输出
        // {"A":"2017-10-12T06:37:59.021+0000","B":"2017-10-12T06:37:59.021+0000","C":"2017-10-12","D":"2017-10-12T14:37:59.021"}
        // 对所有格式都有影响
        // Date 和 Calendar 的时区为 0
        // LocalDate 和 LocalDateTime 无时区信息，并且显示为完整时间格式
        // LocalDateTime 为带 (T) 的格式


        mapper.dateFormat = format
        // 设置 yyyy-MM-dd HH:mm:ss 后输出
        // {"A":"2017-10-12 14:37:59","B":"2017-10-12 14:37:59","C":"2017-10-12","D":"2017-10-12T14:37:59.021"}
        // 对 Date 和 Calendar 有影响
        // 对 LocalDate 和 LocalDateTime 无影响


        // 忽略大小写
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
    }


    fun toJsonString(obj: Any): String {
        try {
            return mapper.writeValueAsString(obj)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return ""
    }

    fun <T> toJsonObject(jsonString: String, cls: Class<T>): T? {
        try {
            return mapper.readValue(jsonString, cls)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return null
    }

}



