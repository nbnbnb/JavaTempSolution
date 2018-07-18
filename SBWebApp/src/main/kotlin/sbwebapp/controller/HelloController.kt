package sbwebapp.controller

import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Created by ZhangJin on 2018/6/12.
 */

@RestController
class HelloController {

    @RequestMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @RequestMapping("/formsbind")
    fun formsBind(@RequestParam info: Map<String, String>): String {
        return "Hello World!"
    }
}