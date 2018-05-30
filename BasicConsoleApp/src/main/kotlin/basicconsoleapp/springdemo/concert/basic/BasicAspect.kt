package basicconsoleapp.springdemo.concert.basic

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.stereotype.Component

// 指定一个切面
// 里面定义切入点（Pointcut）和通知（Advice）
@Aspect
// 声明为一个 bean
@Component
object BasicAspect {

    // 定义一个命名的切点，这样可以避免在每个 Advice 上写重复的切点表达式
    // 方法名称可以随意取
    // 方法的实际内容并不重要， 在这里它实际上应该是空的
    //
    // 切点表达式 execution(* basicconsoleapp.springdemo.concert.basic.Performance.*(..))
    // execution 表示在方法执行的时候触发
    //      * 表示返回任意类型
    //      basicconsoleapp.Performance 表方法的类
    //      * 表示所有的方法
    //      (..) 表示使用任意参数
    @Pointcut("execution(* basicconsoleapp.springdemo.concert.basic.Performance.*(..))")
    fun performance() {
    }

    // 将 @Before Advice 绑定到切入点上
    @Before("performance()")
    fun doBefore(joinPoint: JoinPoint) {
        println("MethodName: ${joinPoint.signature.name} - doBefore")
    }

    // 将 @After Advice 绑定到切入点上
    @After("performance()")
    fun doAfter(joinPoint: JoinPoint) {
        println("MethodName: ${joinPoint.signature.name} - doAfter")
    }

    // 将 @AfterReturning Advice 绑定到切入点上
    @AfterReturning(pointcut = "performance()", returning = "returnVal")
    fun doAfterReturning(joinPoint: JoinPoint, returnVal: Any?) {
        println("MethodName: ${joinPoint.signature.name} - doAfterReturning - ReturnVal: $returnVal")
    }

    // 将 @AfterThrowing Advice 绑定到切入点上
    @AfterThrowing("performance()")
    fun doAfterThrowing(joinPoint: JoinPoint) {
        // MethodName: showError - doBefore
        // execute before error
        // MethodName: showError - doAfter
        // MethodName: showError - doAfterThrowing
        println("MethodName: ${joinPoint.signature.name} - doAfterThrowing")
    }

}
