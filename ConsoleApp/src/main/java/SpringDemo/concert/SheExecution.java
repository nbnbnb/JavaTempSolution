package SpringDemo.concert;

import org.springframework.stereotype.Component;

@Component
public class SheExecution implements IExecution {
    @Override
    public void doIt() {
        System.out.println("SheExecution.doIt()");
    }

}
