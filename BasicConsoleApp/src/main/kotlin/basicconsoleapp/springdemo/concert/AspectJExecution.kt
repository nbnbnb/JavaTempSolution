package basicconsoleapp.springdemo.concert

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
class AspectJExecution {

    // 总共 3 个限制
    @Before("execution(public * basicconsoleapp.springdemo.concert.IExecution.*(..)) && @annotation(basicconsoleapp.springdemo.concert.MyFlag) && within(basicconsoleapp.springdemo.concert.SheExecution)")
    fun doAOP(joinPoint: JoinPoint) {
        println("execution(public * *(..))")
    }
}
