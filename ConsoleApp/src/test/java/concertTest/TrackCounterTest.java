package concertTest;

import concert.Audience;
import concert.ConcertConfig;
import concert.TrackCounter;
import concert.TrackCounterConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {

    @Autowired
    private TrackCounter counter;

    @Test
    public void counterShouldNotBeNull() {
        assertNotNull(counter);
    }

    @Test
    public void testTrackCounter(){
        counter.countTrack(1);
        assertEquals(1,counter.getPlayCount(1));
    }

}
