package basicwebapp.extention.listener

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * Created by ZhangJin on 2017/7/15.
 *
 *
 * 同时继承了 HttpSessionListener 和  HttpSessionListener
 *
 *
 * HttpSessionListener 相关的方法用于注册 session 的创建及销毁方法
 */
@WebListener
class SessionListener : HttpSessionListener, ServletContextListener {

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {

    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {}

    override fun sessionCreated(httpSessionEvent: HttpSessionEvent) {

    }

    override fun sessionDestroyed(httpSessionEvent: HttpSessionEvent) {

    }
}
