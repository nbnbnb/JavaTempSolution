package sbwebapp.entities

import sbwebapp.validator.CaseMode
import sbwebapp.validator.CheckCase
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.*


/**
 * @Null
 * @NotNull
 * @AssertTrue
 * @AssertFalse
 * @Min(value)
 * @Max(value)
 * @DecimalMin(value)
 * @DecimalMax(value)
 * @Size(max,min)
 * @Digits(integer,fraction)
 * @Past 被注释的元素必须是一个 "过去" 的日期
 * @Future 被注释的元素必须是一个 "将来" 的日期
 * @Pattern(value)
 */

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

    @Future(message = "生日必须在当前时间之前")
    var birthday: Date? = null

    @Pattern(regexp = "^(.+)@(.+)$", message = "邮箱的格式不合法")
    var email: String? = null

    @CheckCase(value = CaseMode.LOWER, message = "名字的拼音需要小写")
    var spellName: String? = null
}