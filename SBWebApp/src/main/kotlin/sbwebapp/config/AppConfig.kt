package sbwebapp.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sbwebapp.ext.LoggerFilter

/**
 * Created by ZhangJin on 2018/6/12.
 */

@Configuration
open class AppConfig {
    @Bean
    open fun filterDemo4Registration(): FilterRegistrationBean<*> {

        val registration = FilterRegistrationBean<LoggerFilter>()
        //注入过滤器
        registration.filter = LoggerFilter()
        //拦截规则
        registration.addUrlPatterns("/*")
        //过滤器名称
        registration.setName("LoggerFilter")
        //是否自动注册
        registration.isEnabled = true
        //过滤器顺序
        registration.order = 1

        return registration
    }
}