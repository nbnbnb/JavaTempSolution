package basicconsoleapp.helper

import basicconsoleapp.ext.NetDateTimeDeserializer
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by jinzhanga on 2017/10/12.
 */
object SerializerHelper {

    private val mapper = jacksonObjectMapper()
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    init {
        val javaTimeModule = JavaTimeModule()

        // 默认方式
        // 2019-06-27T14:48:27
        javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))

        // 使用注解方式
        // /Date(1507790279000-0000)/
        // javaTimeModule.addDeserializer(LocalDateTime::class.java, NetDateTimeDeserializer())

        mapper.registerModule(javaTimeModule)
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)  // 不区分大小写
        mapper.dateFormat = format
    }


    fun toJsonString(obj: Any): String {
        return mapper.writeValueAsString(obj)
    }

    fun <T> toJsonObject(jsonString: String, cls: Class<T>): T? {
        return mapper.readValue(jsonString, cls)
    }
}




