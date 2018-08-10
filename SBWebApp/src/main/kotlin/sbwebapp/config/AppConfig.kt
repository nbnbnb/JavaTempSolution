package sbwebapp.config

import javax.xml.ws.Endpoint
import org.apache.cxf.Bus
import org.apache.cxf.jaxws.EndpointImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.caucho.HessianServiceExporter
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter
import sbwebapp.service.inter.HelloWorldService
import sbwebapp.service.impl.HelloWorldServiceImpl


/**
 * Created by ZhangJin on 2018/6/12.
 */

@Configuration
class AppConfig {

    @Autowired
    private lateinit var helloWorldService: HelloWorldService

    @Autowired
    private lateinit var bus: Bus

    //发布服务
    @Bean(name = ["/HessianHelloWorldService"])
    fun hessianServiceExporter(): HessianServiceExporter {
        val exporter = HessianServiceExporter()
        exporter.service = helloWorldService
        exporter.serviceInterface = HelloWorldService::class.java
        return exporter
    }

    //发布服务
    @Bean(name = ["/HttpInvokerHelloWorldService"])
    fun httpInvokerServiceExporter(): HttpInvokerServiceExporter {
        val exporter = HttpInvokerServiceExporter()
        exporter.service = helloWorldService
        exporter.serviceInterface = HelloWorldService::class.java
        return exporter
    }

    @Bean
    fun jaxWsHelloWorldService(): Endpoint {
        // http://cxf.apache.org/docs/springboot.html
        // 参考 Spring Boot CXF JAX-WS Starter

        // demo
        // https://github.com/apache/cxf/tree/master/distribution/src/main/release/samples/jaxws_spring_boot

        // cxf-spring-boot-starter-jaxws 的版本与 SpringBoot 需要兼容

        val endpoint = EndpointImpl(bus, HelloWorldServiceImpl())
        endpoint.publish("/JaxWsHelloWorldService")

        // 服务地址 http://localhost:8080/services/JaxWsHelloWorldService
        // WSDL http://localhost:8080/services/JaxWsHelloWorldService?wsdl
        return endpoint
    }


}
