package Extention.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by ZhangJin on 2017/7/15.
 *
 * 这个 Servlet 是在 DynRegListener 中动态注册的
 * 注意，此处没有添加 @WebServlet 注解
 *
 * 通过 /dynServlet 页面访问
 */
public class DynServlet extends HttpServlet {

    private String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>First servlet</title></head><body>" + name);
        writer.println("</body></html>");
    }

    public void setName(String name) {
        this.name = name;
    }
}
