package demos

import kotlin.coroutines.experimental.buildSequence

fun test() {
    val lazySeq = buildSequence {
        println("START ")
        for (i in 1..5) {
            yield(i)
            print("STEP ")
        }
        println("END")
    }

// 输出序列的前三个元素
    lazySeq.take(3).forEach { println("$it ") }
}

