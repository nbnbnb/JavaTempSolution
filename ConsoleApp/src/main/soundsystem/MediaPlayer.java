package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaPlayer {

    private CDPlayer cd;

    @Autowired
    public MediaPlayer(CDPlayer cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
