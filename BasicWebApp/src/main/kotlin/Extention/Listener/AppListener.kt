package Extention.Listener

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener
import java.util.HashMap

/**
 * Created by ZhangJin on 2017/7/15.
 * 添加一个自定义的监听器
 * 将自定义数据添加到 ServletContext 的属性集合中
 * 然后在 contries.jsp 页面中读取
 *
 * 使用 @WebListener 进行注册
 */
@WebListener
class AppListener : ServletContextListener {

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {
        val servletContext = servletContextEvent.servletContext
        val countries = HashMap<String, String>()
        countries.put("ca", "Canada")
        countries.put("us", "United Status")
        servletContext.setAttribute("countries", countries)
    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {

    }
}
