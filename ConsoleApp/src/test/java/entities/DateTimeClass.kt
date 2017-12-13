package entities

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Created by jinzhanga on 2017/10/12.
 */

// 反序列化时，需要指定默认值，这样才会生成公共的构造函数
// 并且需要使用 var 声明字段，这样才能赋值
data class DateTimeClass(
        @JsonPropertyOrder(alphabetic = true) var A: Date? = null,
        @JsonPropertyOrder(alphabetic = true) var B: Calendar? = null,
        @JsonPropertyOrder(alphabetic = true) var C: LocalDate? = null,
        @JsonPropertyOrder(alphabetic = true) var D: LocalDateTime? = null)

