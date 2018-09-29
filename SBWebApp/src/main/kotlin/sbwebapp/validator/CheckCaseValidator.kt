package sbwebapp.validator


import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass
import javax.validation.ConstraintValidatorContext
import javax.validation.ConstraintValidator

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS)
@Retention
@Constraint(validatedBy = [CheckCaseValidator::class])
@MustBeDocumented
annotation class CheckCase(val value: CaseMode,
                           val message: String = "{sbwebapp.validator.CheckCase}",
                           val groups: Array<KClass<*>> = [],
                           val payload: Array<KClass<out Payload>> = [])

/**
 * 验证类
 */
class CheckCaseValidator : ConstraintValidator<CheckCase, String> {

    private var caseMode: CaseMode? = null

    override
    fun initialize(constraintAnnotation: CheckCase) {
        this.caseMode = constraintAnnotation.value
    }

    override
    fun isValid(obj: String?, constraintContext: ConstraintValidatorContext): Boolean {

        if (obj == null)
            return true

        return if (caseMode === CaseMode.UPPER)
            obj == obj.toUpperCase()
        else
            obj == obj.toLowerCase()
    }
}

enum class CaseMode {
    UPPER,
    LOWER
}

