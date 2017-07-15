import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZhangJin on 2017/7/15.
 */
public class FirstServlet extends HttpServlet {

    private static final long serialVersionUID = -6045338L;

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
