package consoleapp.mindview

import java.util.Random
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.locks.ReentrantLock

internal abstract class Accumulator {
    @Volatile protected var index = 0
    @Volatile protected var value: Long = 0
    protected var duration: Long = 0
    protected var id = "error"

    abstract fun accmumlate()

    abstract fun read(): Long

    private inner class Modifier : Runnable {

        override fun run() {
            for (i in 0 until cycles) {
                accmumlate()
            }
            try {
                barrier.await()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }

    private inner class Reader : Runnable {
        @Volatile private var value: Long = 0

        override fun run() {
            for (i in 0 until cycles) {
                value = read()
            }

            try {
                barrier.await()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }

    fun timedTest() {
        val start = System.nanoTime()
        for (i in 0 until N) {
            exec.execute(Modifier())
            exec.execute(Reader())
        }

        try {
            barrier.await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        duration = System.nanoTime() - start

        System.out.printf("%-13s: %13d\n", id, duration)
    }

    companion object {
        var cycles = 50000L
        private val N = 4
        var exec = Executors.newFixedThreadPool(N * 2)
        private val barrier = CyclicBarrier(N * 2 + 1)
        @JvmStatic
        protected val SIZE = 1000000
        @JvmStatic
        protected var preLoaded = IntArray(SIZE)

        init {
            val random = Random(47)
            for (i in 0 until SIZE) {
                preLoaded[i] = random.nextInt()
            }
        }

        fun report(acc1: Accumulator, acc2: Accumulator) {
            System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id, acc1.duration.toDouble() / acc2.duration.toDouble())
        }
    }
}

internal class BaseLine : Accumulator() {
    init {
        id = "BaseLine"
    }

    override fun accmumlate() {
        value += preLoaded[index++].toLong()
        if (index >= SIZE / 2) {
            index = 0
        }
    }

    override fun read(): Long {
        return value
    }
}

internal class SynchronizedTest : Accumulator() {
    init {
        id = "synchronized"
    }

    @Synchronized override fun accmumlate() {
        value += preLoaded[index++].toLong()
        if (index >= SIZE) {
            index = 0
        }
    }

    @Synchronized override fun read(): Long {
        return value
    }
}

internal class LockTest : Accumulator() {

    private val lock = ReentrantLock()

    init {
        id = "Lock"
    }

    override fun accmumlate() {
        lock.lock()
        try {
            value += preLoaded[index++].toLong()
            if (index >= SIZE) {
                index = 0
            }
        } finally {
            lock.unlock()
        }

    }

    override fun read(): Long {
        lock.lock()
        try {
            return value
        } finally {
            lock.unlock()
        }
    }
}

internal class AtomicTest : Accumulator() {

    private val index_self = AtomicInteger(0)
    private val value_self = AtomicLong(0)

    init {
        id = "Atomic"
    }

    override fun accmumlate() {
        var i = index_self.getAndIncrement()
        value_self.getAndAdd(preLoaded[i].toLong())
        if (++i >= SIZE / 2) {
            index_self.set(0)
        }
    }

    override fun read(): Long {
        return value_self.get()
    }
}

object SynchronizationComparisons {
    internal var baseLine = BaseLine()
    internal var synch = SynchronizedTest()
    internal var lock = LockTest()
    internal var atomic = AtomicTest()

    internal fun test() {
        println("===================================")
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles)
        baseLine.timedTest()
        synch.timedTest()
        lock.timedTest()
        atomic.timedTest()

        Accumulator.report(synch, baseLine)
        Accumulator.report(lock, baseLine)
        Accumulator.report(atomic, baseLine)
        Accumulator.report(synch, lock)
        Accumulator.report(synch, atomic)
        Accumulator.report(lock, atomic)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val iteration = 5
        println("Warmup")
        baseLine.timedTest()

        for (i in 0 until iteration) {
            test()
            Accumulator.cycles *= 2
        }

        Accumulator.exec.shutdown()
    }

}
