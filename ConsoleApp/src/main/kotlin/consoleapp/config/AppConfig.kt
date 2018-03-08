package consoleapp.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@ComponentScan(basePackages = arrayOf("consoleapp/configeapp/config", "consoleapp/demos", "springdemo.concert"))
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
@Configuration
open class AppConfig
