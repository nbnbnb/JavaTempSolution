package sbwebapp.rpc

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import sbwebapp.config.HttpInvokerClientApp
import sbwebapp.service.inter.HelloWorldService
import kotlin.test.assertEquals

/**
 * Created by jinzhanga on 2018/8/10.
 */

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(HttpInvokerClientApp::class)])
class HttpInvokerClientTest {

    @Autowired
    private lateinit var helloWorldService: HelloWorldService

    @Test
    fun sayHello() {
        assertEquals("Hello World! ZhangJin", helloWorldService.sayHello("ZhangJin"))
    }
}