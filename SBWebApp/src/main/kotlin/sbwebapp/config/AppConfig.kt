package sbwebapp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.caucho.HessianServiceExporter
import sbwebapp.service.inter.HelloWorldService


/**
 * Created by ZhangJin on 2018/6/12.
 */

@Configuration
class AppConfig {
    @Autowired
    private lateinit var helloWorldService: HelloWorldService

    //发布服务
    @Bean(name = ["/HelloWorldService"])
    fun accountService(): HessianServiceExporter {
        val exporter = HessianServiceExporter()
        exporter.service = helloWorldService
        exporter.serviceInterface = HelloWorldService::class.java
        return exporter
    }
}
