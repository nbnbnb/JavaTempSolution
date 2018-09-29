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
        bean.birthday = Date()
        bean.friendName = null
        bean.weight = BigDecimal(30)
        bean.email = "xiaogangfan163.com"
        val res = validate(bean)
        assertEquals(res[0], "地址应该在6-30字符之间")
        assertEquals(res[1], "只能为true")
        assertEquals(res[2], "生日必须在当前实践之前")
        assertEquals(res[3], "多吃点饭吧")
        assertEquals(res[4], "邮箱的格式不合法")
        assertEquals(res[5], "名字不能为空")
    }
}