package consoleapp.demos

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun test() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("I'am sleepting $i")
            delay(500)
        }
    }

    delay(1300)
    println("main: I'm tired of waiting!")
    job.cancel()
    job.join()

    println("main: Now I can quit.")
}



