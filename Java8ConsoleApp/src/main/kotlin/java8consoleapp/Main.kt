package java8consoleapp

import java8consoleapp.demos.JavaDemo
import java8consoleapp.demos.KotlinDemo


/**
 * Created by ZhangJin on 2017/7/8.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("----- Java 8 start -----")
        temp()
        println("----- Java 8  end  -----")
    }

    private fun temp() {
        try {
            KotlinDemo.test()
            JavaDemo.test()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}



