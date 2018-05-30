package basicconsoleapp.springdemo.concert.selector

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
object SelectorAspect {

    // 总共 3 个限制
    //
    // public * basicconsoleapp.springdemo.concert.selector.IExecution.*(..)
    //      IExecution 接口下的公共方法，任意参数，任意返回值
    // @annotation(basicconsoleapp.springdemo.concert.selector.MyFlag)
    //      添加了 MyFlag 注解
    // within(basicconsoleapp.springdemo.concert.selector.SheExecution)
    //      并且实现者为 SheExecution 类型
    //
    // 则执行下面的 @Before 注入
    @Before("execution(public * basicconsoleapp.springdemo.concert.selector.IExecution.*(..)) && @annotation(basicconsoleapp.springdemo.concert.selector.MyFlag) && within(basicconsoleapp.springdemo.concert.selector.SheExecution)")
    fun doAOP(joinPoint: JoinPoint) {
        val execution = joinPoint.target as IExecution
        execution.doIt()
    }
}
