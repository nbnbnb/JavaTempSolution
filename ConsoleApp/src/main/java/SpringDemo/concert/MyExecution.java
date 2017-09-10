package SpringDemo.concert;

import org.springframework.stereotype.Component;

@Component
public class MyExecution implements IExecution {
    @Override
    @MyFlag
    public void doIt() {
        System.out.println("MyExecution.doIt()");
    }
}
