package sbwebapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan


@SpringBootApplication
@ServletComponentScan
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
