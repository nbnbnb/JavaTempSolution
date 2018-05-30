package basicconsoleapp.springdemo.concert.selector

import org.springframework.stereotype.Component

@Component
class SheExecution : IExecution {

    // 由于添加了 MyFlag 注解，则会被注入
    @MyFlag
    override fun doIt() {
        println("SheExecution.doIt()")
    }

}
