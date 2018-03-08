package consoleapp.mindview

import java.util.concurrent.locks.ReentrantLock

internal abstract class Incrementable {
    protected var counter: Long = 0

    abstract fun increment()
}

internal class SynchronizingTest : Incrementable() {
    @Synchronized override fun increment() {
        ++counter
    }
}

internal class LockingTest : Incrementable() {
    private val lock = ReentrantLock()

    override fun increment() {
        lock.lock()
        try {
            ++counter
        } finally {
            lock.unlock()
        }
    }
}

object SimpleMicroBanchmark {
    internal fun test(incrementable: Incrementable): Long {
        val start = System.nanoTime()
        for (i in 0..10000000L - 1) {
            incrementable.increment()
        }
        return System.nanoTime() - start
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val syncTime = test(SynchronizingTest())
        val lockTime = test(LockingTest())
        System.out.printf("synchronized: %1$10d\n", syncTime)
        System.out.printf("Lock:         %1$10d\n", lockTime)
        System.out.printf("Lock/synchronized = %1$.3f", lockTime.toDouble() / syncTime.toDouble())
    }
}
