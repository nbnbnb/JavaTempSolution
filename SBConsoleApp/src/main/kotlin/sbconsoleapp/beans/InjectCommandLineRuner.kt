package sbconsoleapp.beans

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/3/8.
 *
 *  应用启动或将会执行此程序
 *  可以通过 Ordered 指定顺序（当有多个 CommandLineRunner 的情况下）
 */

@Component
class InjectCommandLineRuner : CommandLineRunner {

    @Autowired
    lateinit var myProperties: InjectProperties

    override fun run(vararg args: String) {

        println("Staring in InjectCommandLineRuner")
        println(myProperties.name)
        println(myProperties.age)
        println(myProperties.address)
    }
}