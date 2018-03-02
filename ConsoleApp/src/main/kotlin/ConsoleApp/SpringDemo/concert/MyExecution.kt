package ConsoleApp.SpringDemo.concert

import org.springframework.stereotype.Component

@Component
class MyExecution : IExecution {
    @MyFlag
    override fun doIt() {
        println("MyExecution.doIt()")
    }
}