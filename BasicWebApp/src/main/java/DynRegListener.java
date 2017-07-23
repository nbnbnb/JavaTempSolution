import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * Created by ZhangJin on 2017/7/15.
 * 通过监听器来动态注册 Servlet
 */
@WebListener
public class DynRegListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Servlet firstServlet = null;

        try {
            // 创建一个 Servlet
            firstServlet = servletContext.createServlet(FirstServlet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (firstServlet != null && firstServlet instanceof FirstServlet) {
            ((FirstServlet) firstServlet).setName("Dynamic registered servled");

            // 注册 Servlet
            ServletRegistration.Dynamic dynamic = servletContext.addServlet("firstServlet", firstServlet);
            dynamic.addMapping("/dynamic");
        }
    }
}
