package consoleapp.demos

import consoleapp.config.AppConfig
import consoleapp.springdemo.concert.Encoreable
import consoleapp.springdemo.concert.IExecution
import consoleapp.springdemo.concert.Performance
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object SpringDemo {

    fun aopDemo() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val performance = context.getBean(Performance::class.java)
        performance.doIt()

        // 由于实现了“引入新功能”
        // 此处转型为新功能接口
        val encoreable = performance as Encoreable
        encoreable.performEncore()
    }

    fun executionDemo() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val myExecution = context.getBean("myExecution", IExecution::class.java)
        myExecution.doIt()

        val sheExecution = context.getBean("sheExecution", IExecution::class.java)
        sheExecution.doIt()
    }

}
