package sbconsoleapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


/**
 * Created by ZhangJin on 2017/7/8.
 */
@SpringBootApplication
class ConsoleApplication

fun main(args: Array<String>) {
    runApplication<ConsoleApplication>(*args)
}

/*
@SpringBootApplication
class ConsoleApplication {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            SpringApplication.run(arrayOf(ConsoleApplication::class.java), args)
        }
    }
}
*/


