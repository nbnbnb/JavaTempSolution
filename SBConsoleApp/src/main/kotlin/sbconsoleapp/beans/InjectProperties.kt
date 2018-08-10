package sbconsoleapp.beans

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by jinzhanga on 2018/3/8.
 */

/**
 * application.properties 和 application.yml 中的参数将会合并
 * application.properties 的优先级高于 application.yml
 */

@Component
class InjectProperties {
    @Value("\${info.name}")
    lateinit var name: String

    @Value("\${info.age}")
    var age: Int = 0

    @Value("\${info.city}")
    lateinit var address: String

    // 通过命令行指定 --adress=home
    // 则可以通过 @Value("\${address}") 进行注入
}