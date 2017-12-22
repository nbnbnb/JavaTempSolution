package demos

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun test() = runBlocking {

    val job = launch {
        delay(1000L)
        println("World!")
    }

    job.join()

}


