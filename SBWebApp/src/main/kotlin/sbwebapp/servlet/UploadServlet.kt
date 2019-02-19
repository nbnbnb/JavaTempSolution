package sbwebapp.servlet

import sbwebapp.concrete.ReadListenerImpl
import javax.servlet.AsyncListener
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.AsyncEvent

// 添加 asyncSupported=true，支持异步
@WebServlet(name = "UploadServlet", urlPatterns = ["/UploadServlet"], asyncSupported = true)
class UploadServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val context = req!!.startAsync()

        context.addListener(object : AsyncListener {
            override fun onComplete(event: AsyncEvent) {
                event.suppliedResponse.outputStream.print("Complete")
            }

            override fun onError(event: AsyncEvent) {
                println(event.throwable)
            }

            override fun onStartAsync(event: AsyncEvent) {}

            override fun onTimeout(event: AsyncEvent) {
                println("my asyncListener.onTimeout")
            }
        })

        val input = req.inputStream
        val readListener = ReadListenerImpl(input, resp!!, context)
        input.setReadListener(readListener)

    }
}