package soundsystem;

import org.springframework.stereotype.Component;


@Component
public class CDPlayer implements CompactDisc {

    @Override
    public void play() {
        System.out.println("This is the cd player");
    }
}
