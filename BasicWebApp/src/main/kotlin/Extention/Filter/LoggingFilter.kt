package Extention.Filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.annotation.WebInitParam
import javax.servlet.http.HttpServletRequest
import java.io.*
import java.util.Date


/**
 * Created by ZhangJin on 2017/7/15.
 *
 *
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
class LoggingFilter : javax.servlet.Filter {
    private var logger: PrintWriter? = null

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        val logFileName = filterConfig.getInitParameter("logFileName")
        val appPath = filterConfig.servletContext.getRealPath("/")

        println("logFileName: " + logFileName)

        try {
            logger = PrintWriter(File(appPath, logFileName))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            throw ServletException(e.message)
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        println("Extention.Filter.LoggingFilter.doFilter")
        val httpServletRequest = servletRequest as HttpServletRequest
        logger!!.println(Date().toString() + " " + httpServletRequest.requestURI)
        logger!!.flush()
        filterChain.doFilter(servletRequest, servletResponse)  // 调用链下一个处理
    }

    override fun destroy() {
        println("destroying filter")
        if (logger != null) {
            logger!!.close()
        }
    }
}
