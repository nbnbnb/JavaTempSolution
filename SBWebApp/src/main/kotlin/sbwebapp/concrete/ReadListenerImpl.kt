package sbwebapp.concrete

import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue
import javax.servlet.AsyncContext
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletResponse


internal class ReadListenerImpl(input: ServletInputStream, r: HttpServletResponse, c: AsyncContext) : ReadListener {

    private var input: ServletInputStream
    private var res: HttpServletResponse
    private var ac: AsyncContext
    private val queue = LinkedBlockingQueue<String>()

    init {
        this.input = input
        res = r
        ac = c
    }

    /**
     * When an instance of the `ReadListener` is registered with a [ServletInputStream],
     * this method will be invoked by the container the first time when it is possible
     * to read data. Subsequently the container will invoke this method if and only
     * if the [ServletInputStream.isReady] method
     * has been called and has returned a value of `false` *and*
     * data has subsequently become available to read.
     *
     * @throws IOException if an I/O related error has occurred during processing
     */
    @Throws(IOException::class)
    override fun onDataAvailable() {
        println("Data is available")

        val sb = StringBuilder()
        // -1 当做 Flag
        @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
        var len = -1

        val b = ByteArray(1024)

        while (input.isReady) {
            len = input.read(b)
            if (len != -1) {
                sb.append(String(b, 0, len))
            }
        }

        queue.add(sb.toString())
    }

    /**
     * Invoked when all data for the current request has been read.
     *
     * @throws IOException if an I/O related error has occurred during processing
     */
    @Throws(IOException::class)
    override fun onAllDataRead() {
        println("Data is all read")

        // now all data are read, set up a WriteListener to write
        val output = res.outputStream
        val writeListener = WriteListenerImpl(output, queue, ac)
        output.setWriteListener(writeListener)
    }

    /**
     * Invoked when an error occurs processing the request.
     *
     * @param t the throwable to indicate why the read operation failed
     */
    override fun onError(t: Throwable) {
        ac.complete()
        t.printStackTrace()
    }
}