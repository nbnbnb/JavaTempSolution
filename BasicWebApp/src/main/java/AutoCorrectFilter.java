import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created by ZhangJin on 2017/7/15.
 */

@WebFilter(filterName = "AutoCorrectFilter", urlPatterns = {"/*"})
public class AutoCorrectFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        AutoCorrectHttpServletRequestWrapper wrapper =
                new AutoCorrectHttpServletRequestWrapper(httpServletRequest);

        filterChain.doFilter(wrapper, servletResponse);
    }

    public void destroy() {
    }
}

class AutoCorrectHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest httpServletRequest;

    public AutoCorrectHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.httpServletRequest = request;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return value.trim();
    }

}
