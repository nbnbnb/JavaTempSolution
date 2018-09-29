package sbwebapp.entities

import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.*


class Student : Serializable {
    @NotNull(message = "名字不能为空")
    var name: String? = null

    @Size(min = 6, max = 30, message = "地址应该在6-30字符之间")
    var address: String? = null

    @DecimalMax(value = "100.00", message = "体重有些超标哦")
    @DecimalMin(value = "60.00", message = "多吃点饭吧")
    var weight: BigDecimal? = null

    var friendName: String? = null
    private val isHaveFriend: Boolean
        @AssertTrue
        get() = friendName != null

    @Future(message = "生日必须在当前实践之前")
    var birthday: Date? = null

    @Pattern(regexp = "^(.+)@(.+)$", message = "邮箱的格式不合法")
    var email: String? = null
}