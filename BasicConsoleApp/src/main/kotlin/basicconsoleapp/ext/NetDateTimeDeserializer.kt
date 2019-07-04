package basicconsoleapp.ext

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.regex.Pattern

class NetDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {

    companion object {
        private const val regEx = "\\/Date\\((\\d+)([-+])(\\d{4})\\)\\/"
        private val pattern = Pattern.compile(regEx)
    }

    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime? {

        val matcher = pattern.matcher(p.text)

        return if (matcher.matches()) {
            //println(matcher.group(0))   // /Date(1541559225927+8000)/   2018-11-07T10:53:45.927
            //println(matcher.group(1))   // 1561616307000
            //println(matcher.group(2))   // +|-
            //println(matcher.group(3))   // 8000

            // 忽略时区信息
            // 北京时区
            LocalDateTime.ofEpochSecond(matcher.group(1).toLong() / 1000, 0, ZoneOffset.of("+8"))
        } else {
            null
        }

    }

}