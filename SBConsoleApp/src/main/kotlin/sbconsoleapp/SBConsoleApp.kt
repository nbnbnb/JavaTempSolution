package sbconsoleapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


/**
 * Created by ZhangJin on 2017/7/8.
 */

@SpringBootApplication
class SBConsoleApp {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            SpringApplication.run(arrayOf(SBConsoleApp::class.java), args)
        }
    }
}



