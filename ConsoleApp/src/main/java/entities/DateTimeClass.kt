package entities

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

/**
 * Created by jinzhanga on 2017/10/12.
 */
open class DateTimeClass {
    @JvmField
    var A: Date? = null
    @JvmField
    var B: Calendar? = null
    @JvmField
    var C: LocalDate? = null
    @JvmField
    var D: LocalDateTime? = null
}
