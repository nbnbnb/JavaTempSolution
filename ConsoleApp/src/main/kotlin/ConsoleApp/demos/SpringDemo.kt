package ConsoleApp.demos

import ConsoleApp.SpringDemo.concert.Encoreable
import ConsoleApp.SpringDemo.concert.IExecution
import ConsoleApp.SpringDemo.concert.Performance
import ConsoleApp.config.AppConfig
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
