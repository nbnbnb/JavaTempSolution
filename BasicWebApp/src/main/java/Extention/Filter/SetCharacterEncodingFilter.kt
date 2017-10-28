package Extention.Filter

import javax.servlet.*
import java.io.IOException

/**
 * 添加了一个自定义的过滤器，用于设置请求中的编码
 * 如果使用 Spring MVC，已经有内建的过滤器了，直接配置即可
 *
 * 在 web.xml 中进行配置
 *
 * 也可以使用 @WebFilter 进行配置
 */
class SetCharacterEncodingFilter : Filter {
    private var encoding: String? = null
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        this.encoding = filterConfig.getInitParameter("encoding")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        request.characterEncoding = encoding
        chain.doFilter(request, response)
    }

    override fun destroy() {
        this.encoding = null
    }
}
