package basicconsoleapp.config

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
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
class TaskExecutorConfig : AsyncConfigurer {

    // ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor
    override fun getAsyncExecutor(): Executor? = ThreadPoolTaskExecutor().apply {
        corePoolSize = 5
        maxPoolSize = 10
        setQueueCapacity(25)
        initialize()

        // 创建的这个线程池是个前台线程，不会自动关闭
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return null
    }

}