package SpringDemo.concert

import org.springframework.stereotype.Component

@Component
class JJPerformance : Performance {

    override fun perform(): String {
        println("JJ is Playing!!!")
        return "Good Day!"
    }

    override fun doIt() {
        println("Do It....")
    }
}
