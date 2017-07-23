import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebListener
public class SessionListener implements HttpSessionListener, ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userCounter", new AtomicInteger());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger) servletContext.getAttribute("userCounter");

        int userCount = userCounter.incrementAndGet();
        System.out.println("userCount incremented to :" + userCount);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger) servletContext.getAttribute("userCounter");

        int userCount = userCounter.decrementAndGet();
        System.out.println("------------ userCount decremented to :" + userCount);
    }
}
