package basicconsoleapp.springdemo.ee.taskexecutor

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Service
open class AsyncTaskService {

    // @Async 表示这是个异步方法
    // 如果在类级别，则表明该类的所有方法都是异步方法
    // 这里的方法被自动注入使用 ThreadPoolTaskExecutor 作为 TaskExecutor
    @Async
    open fun executeAsyncTaskOne(i: Int) {
        println("执行异步任务 executeAsyncTaskOne： $i")
    }

    @Async
    open fun executeAsyncTaskTwo(i: Int) {
        println("执行异步任务 executeAsyncTaskTwo ${i}")
    }

    @Async
    open fun executeAsyncTaskThree(i: Int) {
        println("执行异步任务 executeAsyncTaskThree ${i}")
    }
}