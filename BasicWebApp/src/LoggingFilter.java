import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;


/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebFilter(
        filterName = "LoggingFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "logFileName", value = "log.txt"),
                @WebInitParam(name = "prefix", value = "URI: ")})
public class LoggingFilter implements javax.servlet.Filter {

    private PrintWriter logger;
    private String prefix;

    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("prefix");
        String logFileName = filterConfig.getInitParameter("logFileName");
        String appPath = filterConfig.getServletContext().getRealPath("/");

        System.out.println("logFileName: " + logFileName);

        try {
            logger = new PrintWriter(new File(appPath, logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            throw new ServletException(e.getMessage());
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoggingFilter.doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
        logger.flush();

        filterChain.doFilter(servletRequest, servletResponse);  // 调用链下一个处理
    }

    public void destroy() {
        System.out.println("destroying filter");
        if (logger != null) {
            logger.close();
        }
    }


}
