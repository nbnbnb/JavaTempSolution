package basicconsoleapp.config

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
// 开启对异步任务的支持
@EnableAsync
open class TaskExecutorConfig {
    
    @Bean
    open fun customAsyncConfigurer(): AsyncConfigurer {
        return object : AsyncConfigurer {
            // ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor
            override fun getAsyncExecutor(): Executor? = ThreadPoolTaskExecutor().apply {
                // 此处都设置为 1，模拟排队
                // 设置不同的参数，查看效果
                corePoolSize = 1
                maxPoolSize = 1
                // 如果超出了容量，调动异步方法时将会抛出 java.util.concurrent.RejectedExecutionException 异常
                setQueueCapacity(100)
                initialize()

                // 创建的这个线程池是个前台线程，不会自动关闭
            }

            override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
                return null
            }
        }
    }
}