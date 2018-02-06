package Extention.Servlet

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.*

/**
 * Created by ZhangJin on 2017/7/15.
 *
 * 这个 Servlet 是在 DynRegListener 中动态注册的
 * 注意，此处没有添加 @WebServlet 注解
 *
 * 通过 /dynServlet 页面访问
 */
class DynServlet : HttpServlet() {

    private var name: String? = null

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        val writer = resp.writer
        writer.println("<html><head><title>First servlet</title></head><body>" + name!!)
        writer.println("</body></html>")
    }

    fun setName(name: String) {
        this.name = name
    }
}
