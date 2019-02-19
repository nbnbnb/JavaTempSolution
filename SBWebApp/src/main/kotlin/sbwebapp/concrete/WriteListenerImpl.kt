package sbwebapp.concrete

import java.io.IOException
import java.util.*
import javax.servlet.AsyncContext
import javax.servlet.ServletOutputStream
import javax.servlet.WriteListener

internal class WriteListenerImpl(sos: ServletOutputStream, q: Queue<*>, c: AsyncContext) : WriteListener {

    private var output: ServletOutputStream
    private var queue: Queue<*>
    private var context: AsyncContext

    init {
        output = sos
        queue = q
        context = c
    }

    /**
     * When an instance of the WriteListener is registered with a [ServletOutputStream],
     * this method will be invoked by the container the first time when it is possible
     * to write data. Subsequently the container will invoke this method if and only
     * if the [ServletOutputStream.isReady] method
     * has been called and has returned a value of `false` and a write
     * operation has subsequently become possible.
     *
     * @throws IOException if an I/O related error has occurred during processing
     */
    @Throws(IOException::class)
    override fun onWritePossible() {
        while (queue.peek() != null && output.isReady) {
            val data = queue.poll() as String
            output.print(data)
        }
        if (queue.peek() == null) {
            context.complete()
        }
    }

    /**
     * Invoked when an error occurs writing data using the non-blocking APIs.
     *
     * @param t the throwable to indicate why the write operation failed
     */
    override fun onError(t: Throwable) {
        context.complete()
        t.printStackTrace()
    }
}