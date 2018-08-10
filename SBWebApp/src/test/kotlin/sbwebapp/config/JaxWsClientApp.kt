package sbwebapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean
import sbwebapp.service.inter.HelloWorldService
import java.net.URL

/**
 * Created by jinzhanga on 2018/8/10.
 */

@Configuration
class JaxWsClientApp {
    @Bean
    fun helloClient(): JaxWsPortProxyFactoryBean {
        val factory = JaxWsPortProxyFactoryBean()
        factory.wsdlDocumentUrl = URL("http://localhost:8080/services/JaxWsHelloWorldService?wsdl")
        factory.serviceInterface = HelloWorldService::class.java

        // 这三个属性，通过查询 wsdl 可以获取
        factory.portName = "MyHelloServicePortName"
        factory.namespaceUri = "http://www.zhangjin.me/impl"
        factory.serviceName = "MyHelloService"

        return factory
    }
}