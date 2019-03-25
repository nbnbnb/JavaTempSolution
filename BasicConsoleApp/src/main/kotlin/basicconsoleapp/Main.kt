package basicconsoleapp

import basicconsoleapp.demos.JavaTemp
import basicconsoleapp.demos.KotlinTemp


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
        KotlinTemp.test()
        JavaTemp.test()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


