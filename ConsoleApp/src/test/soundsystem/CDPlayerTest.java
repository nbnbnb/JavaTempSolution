

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import soundsystem.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest {

    @Autowired
    private  CDPlayer cd;

    @Autowired
    private MediaPlayer md;

    @Autowired
    private QQPlayer qq;

    @Test
    public void cdShouldNotBeNull(){
        assertNotNull(cd);
    }

    @Test
    public void mediaShouldNotBeNull(){
        assertNotNull(md);
    }

    @Test
    public void qqShouldNotBeNull(){
        assertNotNull(qq);
        qq.play();
    }
}
