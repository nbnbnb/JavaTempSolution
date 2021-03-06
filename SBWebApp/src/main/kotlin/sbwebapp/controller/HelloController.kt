package sbwebapp.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import sbwebapp.entity.Book
import sbwebapp.entity.GroupA
import sbwebapp.entity.GroupB
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
    @RequestMapping("/book", method = [RequestMethod.POST])
    fun addBook(@RequestBody @Valid book: Book): String {
        return "ok"
    }

    /**
     * @Validated 是 org.springframework.validation.annotation 下的
     *
     * @Validated 可以替代 @Valid
     *
     * @Validated 相比 @Valid 添加了验证组的功能
     *
     * Default::class 表示是否验证没有设置 group 的字段
     */

    @RequestMapping("/bookA", method = [RequestMethod.POST])
    fun addBookA(@RequestBody @Validated(value = [GroupA::class, Default::class]) book: Book): String {
        return "ok"
    }

    @RequestMapping("/bookB", method = [RequestMethod.POST])
    fun addBookB(@RequestBody @Validated(value = [GroupB::class, Default::class]) book: Book): String {
        return "ok"
    }

}
