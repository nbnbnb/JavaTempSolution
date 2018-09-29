package sbwebapp.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import javax.validation.ConstraintViolation
import javax.validation.Validation.buildDefaultValidatorFactory
import javax.validation.ValidatorFactory
import sbwebapp.entities.Student
import java.math.BigDecimal
import java.util.*
import javax.validation.Validation
import kotlin.collections.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
class ValidateTest {
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
        bean.birthday = Date(118, 0, 1)  // 2018-01-01
        bean.friendName = null
        bean.weight = BigDecimal(30)
        bean.email = "kkking163.com"
        bean.spellName = "KKKing"
        val res = validate(bean)
        assertEquals(res.size, 7)
        assertTrue { res.contains("地址应该在6-30字符之间") }
        assertTrue { res.contains("名字的拼音需要小写") }
        assertTrue { res.contains("只能为true") }
        assertTrue { res.contains("生日必须在当前时间之前") }
        assertTrue { res.contains("多吃点饭吧") }
        assertTrue { res.contains("邮箱的格式不合法") }
        assertTrue { res.contains("名字不能为空") }
    }
}