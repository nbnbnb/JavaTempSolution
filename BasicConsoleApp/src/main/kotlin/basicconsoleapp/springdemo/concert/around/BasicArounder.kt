package basicconsoleapp.springdemo.concert.around

import org.springframework.stereotype.Component

/**
 * Created by jinzhanga on 2018/5/30.
 */
@Component
class BasicArounder : Arounder {
    override fun showInfo(title: String, age: Int): String {
        println("Starting process in showInfo")
        return "BasicArounder: $title - $age"
    }
}