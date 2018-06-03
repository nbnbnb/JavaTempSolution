package basicconsoleapp.springdemo.ee.conditional

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.env.get
import org.springframework.core.type.AnnotatedTypeMetadata

/**
 * Created by ZhangJin on 2018/6/3.
 */

class WindowsCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return context.environment["os.name"].contains("Windows")
    }
}