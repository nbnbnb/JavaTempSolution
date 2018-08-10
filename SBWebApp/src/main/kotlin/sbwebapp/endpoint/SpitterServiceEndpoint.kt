package sbwebapp.endpoint

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.support.SpringBeanAutowiringSupport
import sbwebapp.service.inter.HelloWorldService
import javax.jws.WebMethod
import javax.jws.WebService

/**
 * Created by jinzhanga on 2018/8/10.
 */

@Component
@WebService(name = "SpitterService")
class SpitterServiceEndpoint : SpringBeanAutowiringSupport() {

    @Autowired
    private lateinit var helloWorldService: HelloWorldService

    @WebMethod
    fun sayHello(name: String): String {
        return helloWorldService.sayHello(name)
    }

}