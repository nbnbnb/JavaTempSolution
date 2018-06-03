package basicconsoleapp.config

import basicconsoleapp.springdemo.ee.fortest.TestBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
open class TestConfig {

    @Bean
    @Profile("dev")
    open fun devTestBean(): TestBean {
        return TestBean("from development profile")
    }

    @Bean
    @Profile("prod")
    open fun prodTestBean(): TestBean {
        return TestBean("from production profile")
    }

}