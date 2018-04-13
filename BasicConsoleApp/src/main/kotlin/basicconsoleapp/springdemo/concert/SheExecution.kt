package basicconsoleapp.springdemo.concert

import org.springframework.stereotype.Component

@Component
class SheExecution : IExecution {
    override fun doIt() {
        println("SheExecution.doIt()")
    }

}
