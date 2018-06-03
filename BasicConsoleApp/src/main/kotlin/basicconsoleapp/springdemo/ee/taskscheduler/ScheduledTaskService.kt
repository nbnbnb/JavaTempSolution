package basicconsoleapp.springdemo.ee.taskscheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Service
class ScheduledTaskService {
    val dataFormat = SimpleDateFormat("HH:mm:ss")

    // 固定频率的任务
    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime() {
        println("每隔五秒执行一次 ${dataFormat.format(Date())}")
    }

    // 使用 Linux 中的 cron 格式进行配置
    // second, minute, hour, day of month, month of week, day of week
    // 每天的 11 点 28 分执行
    @Scheduled(cron = "0 28 11 * * *")
    fun fixTimeExecution() {
        println("在指定时间 ${dataFormat.format(Date())} 执行")
    }

}