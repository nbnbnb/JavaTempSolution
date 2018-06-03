package basicconsoleapp.helper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
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
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
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




