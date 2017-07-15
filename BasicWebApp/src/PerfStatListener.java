import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebListener
public class PerfStatListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        long start = (Long) servletRequest.getAttribute("start");
        long end = System.nanoTime();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();

        System.out.println("time token to execute " + uri + ":" + ((end - start) / 1000) + "microseconds");

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

        ServletRequest servletRequest = servletRequestEvent.getServletRequest();

        servletRequest.setAttribute("start", System.nanoTime());

    }
}
