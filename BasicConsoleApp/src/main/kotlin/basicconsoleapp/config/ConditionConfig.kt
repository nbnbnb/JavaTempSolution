package basicconsoleapp.config

import basicconsoleapp.springdemo.ee.conditional.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition::class)
    fun windowsListService(): ListService {
        return WindowsListService()
    }

    @Bean
    @Conditional(LinuxCondition::class)
    fun linuxListService(): ListService {
        return LinuxListService()
    }

}