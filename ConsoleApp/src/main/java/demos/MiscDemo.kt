package demos

import entities.DateTimeClass
import helper.DateHelper
import helper.SerializerHelper

import java.util.Calendar
import java.util.Date

/**
 * Created by jinzhanga on 2017/10/12.
 */
object MiscDemo {
    fun toJsonStringTest() {
        val times = object : DateTimeClass() {
            init {
                val now = 1507790279021L
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = now

                A = Date(now)
                B = calendar
                C = DateHelper.dateToLocalDate(A!!)
                D = DateHelper.dateToLocalDateTime(A!!)
            }
        }
        println(SerializerHelper.toJsonString(times))
    }

    fun toJsonObjectTest() {
        var jsonString = "{\"A\":\"2017-10-12 14:41:17\",\"B\":\"2017-10-12 14:41:17\",\"C\":\"2017-10-12\",\"D\":\"2017-10-12T14:41:17.068\"}"
        var times = SerializerHelper.toJsonObject(jsonString, DateTimeClass::class.java)
        println(times!!.A)
        println(times.B!!.time)
        println(times.C)
        println(times.D)

        println()

        // Date 同样也支持 Timestamp 格式序列化
        jsonString = "{\"A\":1507790279021,\"B\":\"2017-10-12 14:41:17\",\"C\":\"2017-10-12\",\"D\":\"2017-10-12T14:41:17.068\"}"
        times = SerializerHelper.toJsonObject(jsonString, DateTimeClass::class.java)
        println(times!!.A)
        println(times.B!!.time)
        println(times.C)
        println(times.D)
    }

}
