package consoleapp.springdemo.concert

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
class AspectJExecution {

    @Before("execution(public * consoleapp.IExecution.*(..)) && @annotation(consoleapp.MyFlag) && within(consoleapp.SheExecution)")
    fun doAOP(joinPoint: JoinPoint) {
        println("execution(public * *(..))")
    }
}
