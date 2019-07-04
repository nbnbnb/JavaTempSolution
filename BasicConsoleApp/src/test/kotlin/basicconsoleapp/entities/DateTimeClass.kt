package basicconsoleapp.entities

import basicconsoleapp.ext.NetDateTimeDeserializer
import basicconsoleapp.ext.NetDateTimeDeserializerJava
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Created by jinzhanga on 2017/10/12.
 */

class DateTimeClass {
    lateinit var a: Date
    lateinit var b: Calendar
    lateinit var c: LocalDate

    @JsonDeserialize(using = NetDateTimeDeserializer::class)
    lateinit var d: LocalDateTime

    lateinit var e: LocalDateTime
}
