package Extention.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;


/**
 * Created by ZhangJin on 2017/7/15.
 * <p>
 * 使用 @WebFilter 可以自动进行注册
 * 此时不需要在 web.xml 中进行显式注册
 *
 * 记录 jsp 文件访问记录
 */
/*
@WebFilter(
        filterName = "Extention.Filter.LoggingFilter",
        urlPatterns = {"*.jsp"},
        initParams = {@WebInitParam(name = "logFileName", value = "log.txt")})
        */
public class LoggingFilter implements javax.servlet.Filter {
    private PrintWriter logger;

    public void init(FilterConfig filterConfig) throws ServletException {
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
        System.out.println("Extention.Filter.LoggingFilter.doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.println(new Date() + " " + httpServletRequest.getRequestURI());
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
