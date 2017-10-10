package Extention.Listener;

import Extention.Servlet.DynServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * Created by ZhangJin on 2017/7/15.
 * 通过监听器来动态注册 Servlet
 *
 * 通过 /dynServlet 页面访问
 */
@WebListener
public class DynRegListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Servlet dynServlet = null;

        try {
            // 创建一个 Extention.Servlet
            dynServlet = servletContext.createServlet(DynServlet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dynServlet != null && dynServlet instanceof DynServlet) {
            ((DynServlet) dynServlet).setName("Dynamic registered servlet");

            // 注册 Extention.Servlet
            ServletRegistration.Dynamic dynamic = servletContext.addServlet("dynServlet", dynServlet);
            dynamic.addMapping("/dynServlet");
        }
    }
}
