package sbwebapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.caucho.HessianProxyFactoryBean
import sbwebapp.service.inter.HelloWorldService

/**
 * Created by jinzhanga on 2018/8/10.
 */

@Configuration
class HessianClientApp {
    @Bean
    fun helloClient(): HessianProxyFactoryBean {
        val factory = HessianProxyFactoryBean()
        factory.serviceUrl = "http://localhost:8080/HessianHelloWorldService"
        factory.serviceInterface = HelloWorldService::class.java
        return factory
    }
}