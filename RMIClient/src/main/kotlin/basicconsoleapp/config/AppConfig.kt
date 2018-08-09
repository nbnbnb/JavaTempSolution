package basicconsoleapp.config

import basicconsoleapp.rmi.SpitterService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.rmi.RmiProxyFactoryBean


@ComponentScan("basicconsoleapp")
@Configuration
class AppConfig {
    @Bean(name = ["spitterService"])
    fun spitterService(): RmiProxyFactoryBean {
        val proxyFactoryBean = RmiProxyFactoryBean()
        proxyFactoryBean.serviceUrl = "rmi://127.0.0.1:1199/SpitterService"
        proxyFactoryBean.serviceInterface = SpitterService::class.java
        return proxyFactoryBean
    }
}


