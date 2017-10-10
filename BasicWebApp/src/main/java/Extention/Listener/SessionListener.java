package Extention.Listener;

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
 * <p>
 * 同时继承了 HttpSessionListener 和  HttpSessionListener
 * <p>
 * HttpSessionListener 相关的方法用于注册 session 的创建及销毁方法
 */
@WebListener
public class SessionListener implements HttpSessionListener, ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
