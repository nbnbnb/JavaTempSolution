package basicconsoleapp.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import

@ComponentScan("basicconsoleapp")
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
@Configuration
@Import(ELConfig::class)
open class AppConfig

