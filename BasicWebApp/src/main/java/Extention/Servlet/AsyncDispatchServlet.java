package Extention.Servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by ZhangJin on 2017/7/15.
 * <p>
 * 异步的 Servlet，设置 asyncSupported = true
 * <p>
 * 通过 asyncServlet 进行访问
 */

@WebServlet(urlPatterns = {"/asyncServlet"}, asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        final AsyncContext asyncContext = req.startAsync();  // 使用 startAsync 方法开启异步处理

        req.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);
        asyncContext.start(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            req.setAttribute("asyncThread", Thread.currentThread().getName());
            try {
                PrintWriter writer = resp.getWriter();
                writer.println("Async Thread Running");
                writer.println("mainThread" + req.getAttribute("mainThread"));
                writer.println("asyncThread" + req.getAttribute("asyncThread"));
                writer.println(new Date());
            } catch (IOException e) {

            }
            asyncContext.complete();
        });
    }
}
