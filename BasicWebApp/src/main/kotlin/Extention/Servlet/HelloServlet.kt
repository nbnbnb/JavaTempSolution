package Extention.Servlet

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.io.PrintWriter
import java.util.Date

/**
 * 原始的 HttpServlet 接口
 * doGet doDelete doPost doHead doPut doTrace doOptions
 */
@WebServlet(urlPatterns = arrayOf("/helloServlet"))
class HelloServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/plain"
        val writer = resp.writer
        writer.println("Hello World!")
        writer.println(Date())
    }

}
