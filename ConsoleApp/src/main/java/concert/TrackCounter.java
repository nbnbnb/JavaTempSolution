package concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {


    @Pointcut("execution(* concert.CDTracker.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {

    }

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber) {
        System.out.println("TrackCounter: "+trackNumber);
    }


}
