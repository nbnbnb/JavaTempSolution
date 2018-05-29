package basicconsoleapp

import basicconsoleapp.demos.JavaDemo
import basicconsoleapp.demos.JdbcDemo
import basicconsoleapp.demos.KotlinDemo
import basicconsoleapp.demos.SpringDemo


/**
 * Created by ZhangJin on 2017/7/8.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("----- start -----")
        //demos()
        temp()
        SpringDemo.aopDemo()
        SpringDemo.executionDemo()
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



