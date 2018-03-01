package SpringBootApp

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
        temp()
        log("-----  end  -----")
    }

    private fun temp() {
        println("Hello SpringBoot")
        
    }
}



