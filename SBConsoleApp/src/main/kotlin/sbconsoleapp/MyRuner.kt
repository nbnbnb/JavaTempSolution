package sbconsoleapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/3/8.
 */

@Component
class MyRuner : CommandLineRunner {

    @Autowired
    lateinit var myProperties: MyProperties

    override fun run(vararg args: String) {
        println(myProperties.name)
        println(myProperties.age)
    }

}