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
 */

@WebServlet(name = "AsyncDispatchServlet", urlPatterns = {"/asyncDispatch"}, asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    private static final long serizlVersionUID = 222L;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        final AsyncContext asyncContext = req.startAsync();  // 使用 startAsync 方法开启异步处理

        req.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);
        asyncContext.start(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }

                req.setAttribute("workerThread", Thread.currentThread().getName());

                try {
                    PrintWriter writer = resp.getWriter();
                    writer.println("Async Thread Running");
                    writer.println(new Date());
                } catch (IOException e) {

                }

                asyncContext.complete();
            }
        });


        super.doGet(req, resp);
    }
}
