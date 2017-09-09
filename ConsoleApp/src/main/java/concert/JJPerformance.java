package concert;

import org.springframework.stereotype.Component;

@Component
public class JJPerformance implements Performance {

    @Override
    public void perform() {
        System.out.println("JJ is Playing!!!");
    }
}
