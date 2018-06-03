package basicconsoleapp.springdemo.concert.event

import org.springframework.context.ApplicationEvent

/**
 * Created by ZhangJin on 2018/6/3.
 */

class DemoEvent constructor(source: Any,  var msg: String) : ApplicationEvent(source)