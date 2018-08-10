package sbwebapp.service.impl

import org.springframework.stereotype.Service
import sbwebapp.service.inter.HelloWorldService


/**
 * Created by jinzhanga on 2018/8/10.
 */
@Service
class HelloWorldServiceImpl : HelloWorldService {
    override fun sayHello(name: String): String {
        return "Hello World! $name"
    }
}