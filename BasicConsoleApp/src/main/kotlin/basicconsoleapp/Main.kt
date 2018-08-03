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
        println("-----  end  -----")
    }

    private fun demos() {
        JdbcDemo.basicQueryForSQLServer() // 需要连接 SQLServer，并开启 SQL Server Browser 服务
        SpringDemo.basic()
        SpringDemo.selector()
        SpringDemo.introducer()
        SpringDemo.basic()
        SpringDemo.selector()
        SpringDemo.around()
        SpringDemo.el()
        SpringDemo.aware()
        SpringDemo.task()
        SpringDemo.schedule()
        SpringDemo.conditional()
        SpringDemo.dynamicProxy()
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



