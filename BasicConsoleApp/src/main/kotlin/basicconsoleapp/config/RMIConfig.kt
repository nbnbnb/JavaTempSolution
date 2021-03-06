package basicconsoleapp.config

import basicconsoleapp.rmi.SpitterService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.remoting.rmi.RmiServiceExporter

/**
 * Created by jinzhanga on 2018/8/9.
 */

// 服务开启后，不会关闭进程
// 可以注释掉 @Configuration 关闭此功能
// @Configuration
class RMIConfig {
    @Bean
    fun rmiExporter(service: SpitterService): RmiServiceExporter {
        val exporter = RmiServiceExporter()
        exporter.service = service
        // serviceName 属性用来在RMI注册表中注册一个服务
        exporter.setServiceName("SpitterService")
        exporter.serviceInterface = SpitterService::class.java
        exporter.setRegistryPort(1199)  // 设置端口，默认 1099
        return exporter
    }
}