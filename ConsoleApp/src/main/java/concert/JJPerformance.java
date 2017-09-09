package concert;

import org.springframework.stereotype.Component;

@Component
public class JJPerformance implements Performance {

    @Override
    public String perform() {
        System.out.println("JJ is Playing!!!");
        return "Good Day!";
    }

    @Override
    public void doIt() {
        System.out.println("Do It....");
    }
}
