package basicconsoleapp.config

import basicconsoleapp.springdemo.ee.entities.TestBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
class ProfileSwitchConfig {

    @Bean
    @Profile("dev")
    fun devTestBean(): TestBean {
        return TestBean("from development profile")
    }

    @Bean
    @Profile("prod")
    fun prodTestBean(): TestBean {
        return TestBean("from production profile")
    }

}