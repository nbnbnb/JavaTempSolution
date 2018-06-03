package basicconsoleapp.demos

import basicconsoleapp.config.AppConfig
import basicconsoleapp.config.ELConfig
import basicconsoleapp.springdemo.concert.around.Arounder
import basicconsoleapp.springdemo.concert.aware.AwareService
import basicconsoleapp.springdemo.concert.basic.Performance
import basicconsoleapp.springdemo.concert.introducer.Encoreable
import basicconsoleapp.springdemo.concert.selector.IExecution
import org.apache.commons.io.IOUtils
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object SpringDemo {

    fun basic() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val performance = context.getBean(Performance::class.java)
        performance.doIt()
        println("------")
        performance.perform()
        println("------")
        performance.showError()
    }

    fun selector() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val myExecution = context.getBean("myExecution", IExecution::class.java)
        myExecution.doIt()

        val sheExecution = context.getBean("sheExecution", IExecution::class.java)
        sheExecution.doIt()
    }

    fun introducer() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val performance = context.getBean(Performance::class.java)
        // 由于实现了“引入新功能”
        // 此处可以将 Performance 接口转型为 Encoreable 接口
        val encoreable = performance as Encoreable
        encoreable.performEncore()
    }

    fun around() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val arounder = context.getBean(Arounder::class.java)
        arounder.showInfo("JJZhang", 30)
    }

    fun el() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val elConfig = context.getBean(ELConfig::class.java)
        println(elConfig.normal)
        println(elConfig.osNameString)
        println(elConfig.randomNumber)
        println(elConfig.fromAnother)
        println(IOUtils.toString(elConfig.testFile.inputStream, "utf8"))
        println(IOUtils.toString(elConfig.testUrl.inputStream, "utf8"))
        println(elConfig.bookName)
        println(elConfig.env.getProperty("book.author"))
    }

    fun aware(){
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val awareService = context.getBean(AwareService::class.java)
        awareService.outputResult()
    }
}
