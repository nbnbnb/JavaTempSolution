package basicconsoleapp

import basicconsoleapp.config.AppConfig
import basicconsoleapp.rmi.SpitterController
import org.springframework.context.annotation.AnnotationConfigApplicationContext


/**
 * Created by ZhangJin on 2017/7/8.
 */
object RMIClient {
    @JvmStatic
    fun main(args: Array<String>) {
        println("----- start -----")
        temp()
        println("-----  end  -----")
    }

    private fun temp() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val spitterController = applicationContext.getBean(SpitterController::class.java)
        val spitter = spitterController.getSpitter(100)
        println(spitter)
    }
}



