package sbwebapp.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import sbwebapp.entity.Student
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.Validation
import kotlin.collections.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
class ValidateTest {

    // 执行 JSR303 验证
    private fun <T> validate(t: T): List<String> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        val constraintViolations = validator.validate(t)

        val messageList = ArrayList<String>()
        for (constraintViolation in constraintViolations) {
            messageList.add(constraintViolation.message)
        }
        return messageList
    }

    @Test
    fun check() {
        val bean = Student()
        bean.name = null
        bean.address = "北京"
        // 使用了 @Future 进行标记
        // 验证传入的时间必须在当前之前之后
        bean.nextBirthday = LocalDate.of(2018, 1, 1)
        bean.friendName = null
        bean.weight = BigDecimal(30)
        bean.email = "kkking163.com"
        bean.spellName = "KKKing"

        val res = validate(bean)

        assertEquals(res.size, 7)
        assertTrue { res.contains("地址应该在6-30字符之间") }
        assertTrue { res.contains("名字的拼音需要小写") }
        assertTrue { res.contains("只能为true") }
        assertTrue { res.contains("下一个生日必须在当前时间之后") }
        assertTrue { res.contains("多吃点饭吧") }
        assertTrue { res.contains("邮箱的格式不合法") }
        assertTrue { res.contains("名字不能为空") }
    }
}