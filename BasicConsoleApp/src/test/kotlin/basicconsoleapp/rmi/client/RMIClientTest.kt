package basicconsoleapp.rmi.client


import config.RMIConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import kotlin.test.assertEquals


/**
 * Created by ZhangJin on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RMIConfig::class])
class RMIClientTest {

    @Test
    fun getSpitter() {
        val applicationContext = AnnotationConfigApplicationContext(RMIConfig::class.java)
        val spitterController = applicationContext.getBean(SpitterController::class.java)
        val spitter = spitterController.getSpitter(100)
        assertEquals(100, spitter.id)
    }

}
