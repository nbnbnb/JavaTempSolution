package sbwebapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean
import sbwebapp.service.inter.HelloWorldService

/**
 * Created by jinzhanga on 2018/8/10.
 */

@Configuration
class HttpInvokerClientApp {
    @Bean
    fun helloClient(): HttpInvokerProxyFactoryBean {
        val factory = HttpInvokerProxyFactoryBean()
        factory.serviceUrl = "http://localhost:8080/HttpInvokerHelloWorldService"
        factory.serviceInterface = HelloWorldService::class.java
        return factory
    }
}