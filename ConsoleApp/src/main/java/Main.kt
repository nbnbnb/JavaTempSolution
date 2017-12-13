import demos.JdbcDemo

import demos.SpringDemo
import demos.test


/**
 * Created by ZhangJin on 2017/7/8.
 */
object Main {
    private fun log(obj: Any?) {
        println(obj)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        log("----- start -----")
        //demos()
        temp()
        log("-----  end  -----")
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



