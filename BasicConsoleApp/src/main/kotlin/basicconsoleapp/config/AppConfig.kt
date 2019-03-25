package basicconsoleapp.config

import org.springframework.context.annotation.*
import org.springframework.scheduling.annotation.EnableScheduling


// 定义扫描的路径从中找出标识了需要装配的类自动装配到 Spring 的 bean 容器中
// 如果不指定包路径，默认就是当前类的包
@ComponentScan("basicconsoleapp")
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理

// 由于其他的 @Configuration 都在 basicconsoleapp 包下
// 所有此处不需要进行聚合导入
// @Import(ELConfig::class, TaskExecutorConfig::class, RMIConfig::class)
// 聚合 xml 格式的配置
// @ImportResource("classpath:abc.xml")

// 此注解用于开启 Spring Scheduling
// @EnableScheduling

// 表明这个一个配置类，里面有一个或多个用 @Bean 标记的方法
// 用于配置自定义的 Bean
@Configuration
open class AppConfig



