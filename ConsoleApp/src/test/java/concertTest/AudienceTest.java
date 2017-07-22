package concertTest;

import concert.Audience;
import concert.ConcertConfig;
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
    private Audience audience;

    @Test
    public void audienceShouldNotBeNull() {
        assertNotNull(audience);
    }

    @Test
    public void execActions() {
        audience.silenceCellPhone();
    }
}
