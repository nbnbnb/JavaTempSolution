package sbconsoleapp

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
class MyProperties {
    @Value("\${name}")
    lateinit var name: String

    @Value("\${age}")
    lateinit var age: Integer

    // 通过命令行指定 --adress=home
    @Value("\${address}")
    lateinit var address: String
}