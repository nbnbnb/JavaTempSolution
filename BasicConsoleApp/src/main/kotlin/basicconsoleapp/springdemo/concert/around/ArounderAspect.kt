package basicconsoleapp.springdemo.concert.around

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

/**
 * Created by jinzhanga on 2018/5/30.
 */

@Aspect
@Component
object ArounderAspect {
    @Around("execution(public String basicconsoleapp.springdemo.concert.around.Arounder.showInfo(..))")
    fun doAround(joinPoint: ProceedingJoinPoint) {
        try {
            // 当要将控制权交给被通知的方法时， 它需要调用 ProceedingJoinPoint 的 proceed() 方法
            println("@Before doAround")
            val res = joinPoint.proceed()
            println("@End doAround")
            println("MethodName: ${joinPoint.signature.name} | doAround | ReturnVal: $res")
        } catch (throwable: Throwable) {
            // 可以捕获方法执行中的一次
            // @AfterThrowing 方法可以捕获异常，但是不能处理它
            System.out.println("Catch exception in doArounc")
        }
    }
}