package config

import basicconsoleapp.rmi.SpitterService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.rmi.RmiProxyFactoryBean


@ComponentScan("basicconsoleapp.rmi.client")
@Configuration
class RMIConfig {
    @Bean(name = ["spitterService"])
    fun spitterService(): RmiProxyFactoryBean {
        // RmiProxyFactoryBean 一定要在 Spring 上下文中创建
        val proxyFactoryBean = RmiProxyFactoryBean()
        proxyFactoryBean.serviceUrl = "rmi://localhost:1199/SpitterService"
        proxyFactoryBean.serviceInterface = SpitterService::class.java
        return proxyFactoryBean
    }
}


