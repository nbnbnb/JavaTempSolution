package concert;

import org.springframework.stereotype.Component;

@Component
public class SimpleCDTracker implements CDTracker {
    @Override
    public void playTrack(int trackNumber) {
        System.out.println("I am in trackNumber:" +trackNumber);
    }
}
