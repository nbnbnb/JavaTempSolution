package basicconsoleapp.springdemo.concert.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * Created by ZhangJin on 2018/6/2.
 */

@Service
class DemoService {
    @Value("其他类的属性")
    lateinit var another: String
}