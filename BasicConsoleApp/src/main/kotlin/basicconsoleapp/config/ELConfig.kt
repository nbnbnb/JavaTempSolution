package basicconsoleapp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource

/**
 * Created by ZhangJin on 2018/6/2.
 */


// 里面的属性都是 EL 注入进去的
@Configuration
// 导入外部属性源
// 这样就能通过 ${book.name} 语法进行注入了
// 外部源中的值，同样也会填充到 Environment 中
@PropertySource("test.properties")
open class ELConfig {

    // 注入一个字符串常量
    @Value("I Love You!")
    lateinit var normal: String

    // 注入 systemProperties
    @Value("#{systemProperties['os.name']}")
    lateinit var osNameString: String

    // 生成一个随机数
    @Value("#{T(java.lang.Math).random() * 100.0}")
    var randomNumber: Double = 0.0

    // 注入一个 Bean 对象中属性
    // 此处直接使用了 Bean 对象的默认名称
    @Value("#{demoService.another}")
    lateinit var fromAnother: String

    // 包中的文件
    // 注入为 InputStream
    @Value("test.txt")
    lateinit var testFile: Resource

    // 远程 URL 访问
    // 注入为 InputStream
    @Value("http://www.baidu.com")
    lateinit var testUrl: Resource

    // 直接注入 test.properties 中的数据
    // 由于 Kotlin 语法的关系，此处需要对 $ 进行转义
    @Value("\${book.name}")
    lateinit var bookName: String

    // test.properties 中的数据
    // 也会注入到 Environment 中
    @Autowired
    lateinit var env: Environment

}