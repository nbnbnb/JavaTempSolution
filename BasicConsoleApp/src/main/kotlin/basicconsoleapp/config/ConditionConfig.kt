package basicconsoleapp.config

import basicconsoleapp.springdemo.ee.conditional.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
open class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition::class)
    open fun windowsListService(): ListService {
        return WindowsListService()
    }

    @Bean
    @Conditional(LinuxCondition::class)
    open fun linuxListService(): ListService {
        return LinuxListService()
    }

}