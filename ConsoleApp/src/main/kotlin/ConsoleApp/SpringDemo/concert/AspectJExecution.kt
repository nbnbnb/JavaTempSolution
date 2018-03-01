package ConsoleApp.SpringDemo.concert

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
class AspectJExecution {

    @Before("execution(public * ConsoleApp.IExecution.*(..)) && @annotation(ConsoleApp.MyFlag) && within(ConsoleApp.SheExecution)")
    fun doAOP(joinPoint: JoinPoint) {
        println("execution(public * *(..))")
    }
}
