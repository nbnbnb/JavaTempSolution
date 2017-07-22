package concertTest;

import concert.ConcertConfig;
import concert.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class AudienceTest {

    @Autowired
    private Performance performance;

    @Test
    public void performanceShouldNotBeNull() {
        assertNotNull(performance);
    }

    @Test
    public void execActions() {
        performance.perform();
    }
}
