package sbwebapp.service.impl

import org.springframework.stereotype.Service
import sbwebapp.service.inter.HelloWorldService
import javax.jws.WebMethod


/**
 * Created by jinzhanga on 2018/8/10.
 */
@Service
@javax.jws.WebService(
        serviceName = "MyHelloService",
        portName = "MyHelloServicePortName",
        targetNamespace = "http://www.zhangjin.me/impl",
        endpointInterface = "sbwebapp.service.inter.HelloWorldService")
class HelloWorldServiceImpl : HelloWorldService {
    @WebMethod
    override fun sayHello(name: String): String {
        return "Hello World! $name"
    }
}