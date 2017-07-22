package concert;

import org.springframework.stereotype.Component;

@Component
public class SimplePerformance implements Performance {
    @Override
    public void perform() {
        System.out.println("do Performance in SimplePerformance");
    }
}
