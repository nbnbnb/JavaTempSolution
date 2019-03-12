package basicconsoleapp

import basicconsoleapp.demos.JavaDemo
import basicconsoleapp.demos.KotlinDemo


/**
 * Created by ZhangJin on 2017/7/8.
 */
fun main() {
    println("----- start -----")
    temp()
    println("-----  end  -----")
}

private fun temp() {
    try {
        KotlinDemo.test()
        JavaDemo.test()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


