package Extention.Filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 添加了一个自定义的过滤器，用于设置请求中的编码
 * 如果使用 Spring MVC，已经有内建的过滤器了，直接配置即可
 *
 * 在 web.xml 中进行配置
 *
 * 也可以使用 @WebFilter 进行配置
 */
public class SetCharacterEncodingFilter implements Filter {
    private String encoding = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.encoding = null;
    }
}
