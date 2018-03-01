package Extention.Servlet

import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by ZhangJin on 2017/7/15.
 *
 *
 * 异步的 Servlet，设置 asyncSupported = true
 *
 *
 * 通过 asyncServlet 进行访问
 */

@WebServlet(urlPatterns = arrayOf("/asyncServlet"), asyncSupported = true)
class AsyncDispatchServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        val asyncContext = req.startAsync()  // 使用 startAsync 方法开启异步处理

        req.setAttribute("mainThread", Thread.currentThread().name)
        asyncContext.timeout = 5000
        asyncContext.start {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            req.setAttribute("asyncThread", Thread.currentThread().name)

            val writer = resp.writer
            writer.println("Async Thread Running")
            writer.println("mainThread" + req.getAttribute("mainThread"))
            writer.println("asyncThread" + req.getAttribute("asyncThread"))
            writer.println(Date())

            asyncContext.complete()
        }
    }
}
