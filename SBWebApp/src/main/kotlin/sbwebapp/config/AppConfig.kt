package sbwebapp.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sbwebapp.ext.filter.LoggerFilter


/**
 * Created by ZhangJin on 2018/6/12.
 */

@Configuration
class AppConfig {

    @Bean
    fun loggerFilterRegistration(): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean<LoggerFilter>()
        registration.filter = LoggerFilter()
        registration.addUrlPatterns("/*")
        //registration.addInitParameter("paramName", "paramValue")
        registration.setName("loggerFilter")
        registration.order = 1
        return registration
    }

}
