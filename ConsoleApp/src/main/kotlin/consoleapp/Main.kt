package consoleapp

import consoleapp.demos.JavaDemo
import consoleapp.demos.JdbcDemo
import consoleapp.demos.KotlinDemo
import consoleapp.demos.SpringDemo


/**
 * Created by ZhangJin on 2017/7/8.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("----- start -----")
        //demos()
        temp()
        println("-----  end  -----")
    }

    private fun demos() {
        JdbcDemo.basicQueryForSQLServer() // 需要连接 SQLServer，并开启 SQL Server Browser 服务
        SpringDemo.aopDemo()
        SpringDemo.executionDemo()
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



