package sbwebapp.entity

import sbwebapp.validator.CaseMode
import sbwebapp.validator.CheckCase
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.*


/**
 * https://www.jianshu.com/p/001453283e72
 * https://www.cnblogs.com/beiyan/p/5946345.html
 * https://www.cnblogs.com/xiaogangfan/p/5987659.html
 *
<!--jsr 303-->
<dependency>
<groupId>javax.validation</groupId>
<artifactId>validation-api</artifactId>
<version>1.1.0.Final</version>
</dependency>

<!-- hibernate validator-->
<dependency>
<groupId>org.hibernate</groupId>
<artifactId>hibernate-validator</artifactId>
<version>5.2.0.Final</version>
</dependency>

 * JSR303
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
 *

 * Hibernate validator 在 JSR303 的基础上对校验注解进行了扩展
 * @Email
 * @Length
 * @NotEmpty
 * @Range
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

    @Future(message = "下一个生日必须在当前时间之后")
    var nextBirthday: LocalDate? = null

    @Pattern(regexp = "^(.+)@(.+)$", message = "邮箱的格式不合法")
    var email: String? = null

    // 自定义 Validator
    @CheckCase(value = CaseMode.LOWER, message = "名字的拼音需要小写")
    var spellName: String? = null
}