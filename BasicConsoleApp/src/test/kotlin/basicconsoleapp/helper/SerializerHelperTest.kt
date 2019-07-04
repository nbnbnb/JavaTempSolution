package basicconsoleapp.helper

import basicconsoleapp.entities.DateTimeClass
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import java.util.Calendar


/**
 * Created by jinzhanga on 2017/12/13.
 */


class SerializerHelperTest {

    private var testTime: DateTimeClass = DateTimeClass().apply {
        val time = 1507790279000L  // 2017-10-12T14:37:59
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        a = Date(time)
        b = calendar
        c = DateTimeHelper.dateToLocalDate(a)
        d = DateTimeHelper.dateToLocalDateTime(a)
        e = DateTimeHelper.dateToLocalDateTime(a)
    }

    @Test
    fun toJsonStringTest() {

        // Arrange
        val jsonString = "{\"a\":\"2017-10-12 14:37:59\",\"b\":\"2017-10-12 14:37:59\",\"c\":\"2017-10-12\",\"d\":\"2017-10-12T14:37:59\",\"e\":\"2017-10-12T14:37:59\"}"

        // Act
        val jsonStringNew = SerializerHelper.toJsonString(testTime)

        // Assert
        assertEquals(jsonString, jsonStringNew)

    }

    @Test
    fun toObjectTest() {

        val jsonString = """
            {
                "a": "2017-10-12 14:37:59",
                "b": "2017-10-12 14:37:59",
                "c": "2017-10-12",
                "d": "/Date(1507790279000-0000)/",
                "e": "2017-10-12T14:37:59"
            }
            """
        // Act
        val jsonObjectValue = SerializerHelper.toJsonObject(jsonString, DateTimeClass::class.java)

        // Assert
        // {"A":"2017-10-12 14:37:59","B":"2017-10-12 14:37:59","C":"2017-10-12","D":"2017-10-12T14:37:59"}
        // LocalDateTime 的完整格式为 "2017-10-12T14:37:59.123"，如果毫秒为 0，则省略后面的毫秒形式 "2017-10-12T14:37:59"
        assertEquals(jsonObjectValue!!.a, testTime.a)
        // Calendar 不支持相对性比较，此处比较毫秒值
        assertEquals(jsonObjectValue.b.time, testTime.b.time)
        assertEquals(jsonObjectValue.c, testTime.c)
        assertEquals(jsonObjectValue.d, testTime.d)
    }

    @Test
    fun dotNetDateTimeTest() {

    }

}






