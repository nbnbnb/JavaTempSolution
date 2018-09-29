package sbwebapp.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import sbwebapp.entities.Book
import sbwebapp.entities.GroupA
import sbwebapp.entities.GroupB
import javax.validation.groups.Default


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

    /**
     *  @Valid 是 javax.validation 包下的
     */
    @RequestMapping(value = "/book", method = [RequestMethod.POST])
    fun addBook(@RequestBody @Valid book: Book): String {
        return "ok"
    }

    /**
     * @Validated 是 org.springframework.validation.annotation 下的
     *
     * @Validated 可以替代 @Valid
     *
     * Default::class 表示是否验证没有设置 group 的字段
     */
    @RequestMapping(value = "/bookA", method = [RequestMethod.POST])
    fun addBookA(@RequestBody @Validated(value = [GroupA::class, Default::class]) book: Book): String {
        return "ok"
    }

    @RequestMapping(value = "/bookB", method = [RequestMethod.POST])
    fun addBookB(@RequestBody @Validated(value = [GroupB::class, Default::class]) book: Book): String {
        return "ok"
    }
}
