package basicconsoleapp.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling

@ComponentScan("basicconsoleapp")
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
//@EnableScheduling  // Spring Scheduling
@Configuration
@Import(ELConfig::class, TaskExecutorConfig::class)
class AppConfig

