package basicwebapp.extention.listener

import basicwebapp.extention.servlet.DynServlet

import javax.servlet.*
import javax.servlet.annotation.WebListener

/**
 * Created by ZhangJin on 2017/7/15.
 * 通过监听器来动态注册 servlet
 *
 * 通过 /dynServlet 页面访问
 */
@WebListener
class DynRegListener : ServletContextListener {

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {

    }

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {
        val servletContext = servletContextEvent.servletContext
        var dynServlet: Servlet? = null

        try {
            // 创建一个 basicwebapp.extention.servlet
            dynServlet = servletContext.createServlet<DynServlet>(DynServlet::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (dynServlet != null && dynServlet is DynServlet) {
            dynServlet.setName("Dynamic registered servlet")

            // 注册 basicwebapp.extention.servlet
            val dynamic = servletContext.addServlet("dynServlet", dynServlet)
            dynamic.addMapping("/dynServlet")
        }
    }
}
