package basicconsoleapp.config

import org.springframework.context.annotation.*
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@ComponentScan(basePackages = ["basicconsoleapp"])
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
@Configuration
@Import(ELConfig::class)
open class AppConfig {
    @Bean
    open fun kkking(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}