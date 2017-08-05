package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {

    // performance()方法的实际内容并不重要， 在这里它实际上应该是空的。
    // 其实该方法本身只是一个标识， 供@Pointcut注解依附。

    @Pointcut("execution(* concert.Performance.perform(..))")
    public void performance() {

    }

    /*
    @Before("performance()")
    public void silenceCellPhone() {
        System.out.println("Silencing call phones");
    }

    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }
    */

    // Around 方法和上面的等价

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint jp) {
        try {
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            jp.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
        } catch (Throwable throwable) {
            System.out.println("Demand a refund");
        }
    }

}
