package consoleapp

import consoleapp.demos.JdbcDemo

import consoleapp.demos.SpringDemo
import consoleapp.demos.test


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
            test()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}



