package SpringDemo.concert;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
public class AspectJExecution {

    @Before("execution(public * SpringDemo.concert.IExecution.*(..)) && @annotation(SpringDemo.concert.MyFlag) && within(SpringDemo.concert.SheExecution)")
    public void doAOP(JoinPoint joinPoint) {
        System.out.println("execution(public * *(..))");
    }
}
