package basicwebapp

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by jinzhanga on 2018/4/13.
 */

@WebServlet(name = "HelloServlet", urlPatterns = ["hello"], loadOnStartup = 1)
class HelloServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.writer.print("Hello, World!")
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        var name = request.getParameter("name")
        if (name == null) name = "World"
        request.setAttribute("user", name)
        request.getRequestDispatcher("response.jsp").forward(request, response)
    }
}