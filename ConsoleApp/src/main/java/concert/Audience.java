package concert;

import org.aspectj.lang.annotation.*;

// 指定一个切面
@Aspect
public class Audience {

    // 定义一个命名的切点
    // 方法名称可以随意取
    // 方法的实际内容并不重要， 在这里它实际上应该是空的
    // 其实该方法本身只是一个标识， 供 @Pointcut 注解依附
    @Pointcut("execution(* concert.Performance.perform(..))")
    public void performance() {
    }

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
}
