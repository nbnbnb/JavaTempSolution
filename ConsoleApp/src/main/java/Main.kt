import demos.*
import demos.MiscDemo
import demos.SpringDemo


/**
 * Created by ZhangJin on 2017/7/8.
 */
object Main {
    private fun log(`object`: Any) {
        println(`object`)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        log("----- start -----")
        demo()
        temp()
        log("-----  end  -----")
    }

    fun demo() {
        MiscDemo.toJsonObjectTest()
        JdbcDemo.basicQueryForSQLServer() //需要连接 SQLServer
        MiscDemo.toJsonStringTest()
        SpringDemo.aopDemo()
        SpringDemo.executionDemo()
    }

    fun temp() {
        try {
            test()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}



