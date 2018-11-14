package basicconsoleapp.springdemo.concert.basic

import org.springframework.stereotype.Component

@Component
class BasicPerformance : Performance {

    override fun perform(): String {
        println("Actual Method perform() Call")
        return "Good Day!"
    }

    override fun doIt() {
        println("Actual Method doIt() Call")
    }

    override fun showError() {
        println("execute before error")
        throw NullPointerException("Test null exception")
        @Suppress("UNREACHABLE_CODE")
        println("execute after error")
    }

}
