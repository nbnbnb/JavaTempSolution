package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CDPlayer implements CompactDisc {

    @Override
    public void play() {
        System.out.println("This is the cd player");
    }
}
