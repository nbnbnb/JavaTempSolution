package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 指定一个切面
@Aspect
@Component
public class Audience {

    // 定义一个命名的切点
    // 方法名称可以随意取
    // 方法的实际内容并不重要， 在这里它实际上应该是空的
    // 其实该方法本身只是一个标识， 供 @Pointcut 注解依附
    // 切点表达式
    // execution 表示在方法执行的时候触发
    // * 表示返回任意类型
    // concert.Performance 表方法的类
    // * 表示所有的方法
    // (..) 表示使用任意参数
    @Pointcut("execution(* concert.Performance.*(..))")
    public void performance() {
    }

    /*
    @Before("performance())")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Before("performance())")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    @AfterReturning("performance())")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("performance())")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

    */

    // 环绕通知方法
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("AspectJ Method Is: " + joinPoint.getSignature().getName());
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            // 当要将控制权交给被通知的方法时， 它需要调用 ProceedingJoinPoint 的 proceed() 方法
            Object res = joinPoint.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
            System.out.println("Result is: " + res);
        } catch (Throwable throwable) {
            System.out.println("Demanding a refund");
        }
    }
}
