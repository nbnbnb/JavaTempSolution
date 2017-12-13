package helper

import entities.DateTimeClass
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue


/**
 * Created by jinzhanga on 2017/12/13.
 */

class SerializerHelperTest {
    private val jsonString = "{\"A\":\"2017-10-12 14:37:59\",\"B\":\"2017-10-12 14:37:59\",\"C\":\"2017-10-12\",\"D\":\"2017-10-12T14:37:59.021\"}"
    private val testDate = Date(1507790279021L)
    private val testCalendar: Calendar by lazy {
        val tp = Calendar.getInstance()
        tp.timeInMillis = 1507790279021L
        tp
    }
    private val testTimes: DateTimeClass by lazy {
        DateTimeClass(testDate, testCalendar, DateTimeHelper.dateToLocalDate(testDate), DateTimeHelper.dateToLocalDateTime(testDate))
    }

    @Test
    fun toJsonStringTest() {
        // 由于使用了 Backing Field
        // 反序列化出来都是小写的字段
        // {"a":"2017-10-12 14:37:59","b":"2017-10-12 14:37:59","c":"2017-10-12","d":"2017-10-12T14:37:59.021"}
        // 此处转换为大写进行比较
        assertEquals(jsonString.toUpperCase(), SerializerHelper.toJsonString(testTimes).toUpperCase())
    }

    @Test
    fun toJsonObjectTest() {
        val newTimes = SerializerHelper.toJsonObject(jsonString, DateTimeClass::class.java)!!
        // 转换为相应的日期格式后，毫秒相关的精度将会丢失
        assertTrue(Math.abs(newTimes.A!!.time - testTimes.A!!.time) < 100)
        assertTrue(Math.abs(newTimes.B!!.time.time - testTimes.B!!.time.time) < 100)
        // LocalDate 和 LocalDateTime 可以比较值相等（精度不带毫秒部分）
        assertEquals(newTimes.C, testTimes.C)
        assertEquals(newTimes.D, testTimes.D)

    }
}