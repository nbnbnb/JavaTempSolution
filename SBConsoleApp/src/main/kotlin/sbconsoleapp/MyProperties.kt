package sbconsoleapp

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by jinzhanga on 2018/3/8.
 */

@Component
class MyProperties {
    @Value("\${name}")
    lateinit var name: String

    @Value("\${age}")
    lateinit var age: Integer
}