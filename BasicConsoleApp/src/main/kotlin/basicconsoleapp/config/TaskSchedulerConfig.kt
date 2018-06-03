package basicconsoleapp.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Configuration
// 开启对计划任务的支持
@EnableScheduling
@ComponentScan("basicconsoleapp")
open class TaskSchedulerConfig