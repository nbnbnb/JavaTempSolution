import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebListener
public class AppListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Map<String, String> countries = new HashMap<String, String>();

        countries.put("ca", "Canada");
        countries.put("us", "United Status");

        servletContext.setAttribute("countries", countries);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
