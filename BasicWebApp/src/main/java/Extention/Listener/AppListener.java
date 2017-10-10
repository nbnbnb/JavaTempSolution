package Extention.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangJin on 2017/7/15.
 * 添加一个自定义的监听器
 * 将自定义数据添加到 ServletContext 的属性集合中
 * 然后在 contries.jsp 页面中读取
 *
 * 使用 @WebListener 进行注册
 */
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Map<String, String> countries = new HashMap<>();
        countries.put("ca", "Canada");
        countries.put("us", "United Status");
        servletContext.setAttribute("countries", countries);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
