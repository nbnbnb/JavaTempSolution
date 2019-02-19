package sbwebapp.filter

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.util.StreamUtils
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.net.URLDecoder
import java.nio.charset.StandardCharsets.UTF_8
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse


/**
 * Created by ZhangJin on 2018/6/12.
 */

// 使用 @WebFilter 的方式设置 Filter
// @WebFilter(urlPatterns = ["/*"], filterName = "loginFilter")

// 不支持异步 IO（UploadServlet）
class LoggerFilter : Filter {

    private val logger = LoggerFactory.getLogger(LoggerFilter::class.java)!!

    override fun init(filterConfig: FilterConfig?) {

    }

    override fun destroy() {

    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {

        val requestWrapper = ContentCachingRequestWrapper(servletRequest as HttpServletRequest)
        val responseWrapper = ContentCachingResponseWrapper(servletResponse as HttpServletResponse)

        val requestUrl = requestWrapper.requestURL.toString()

        if (requestUrl.endsWith(".ico")) {
            filterChain.doFilter(servletRequest, servletResponse)
        } else {

            // 放在第一行
            filterChain.doFilter(requestWrapper, responseWrapper)

            val requestBody = URLDecoder.decode(requestWrapper.contentAsByteArray.toString(UTF_8), UTF_8.name())

            val httpMethod = HttpMethod.valueOf(requestWrapper.method)
            val queryString = URLDecoder.decode(requestWrapper.queryString ?: "", UTF_8.name())

            val responseStatus = HttpStatus.valueOf(responseWrapper.statusCode)

            // header 不支持中文，需要进行编码
            val responseHeaders = HttpHeaders()
            for (headerName in responseWrapper.headerNames) {
                responseHeaders.add(headerName, responseWrapper.getHeader(headerName))
            }
            val responseBody = StreamUtils.copyToString(responseWrapper.contentInputStream, UTF_8)

            logger.info("Request-ContentType: ${requestWrapper.contentType}")
            logger.info("Request-Method: $httpMethod")
            logger.info("Request-Body: $requestBody")
            logger.info("Request-Url: $requestUrl")
            logger.info("Request-QueryString: $queryString")
            logger.info("Request-Headers: \n${getRequestHeaderInfos(requestWrapper)}")

            logger.info("Response-ContentType: ${responseWrapper.contentType}")
            logger.info("Response-Body: $responseBody")
            logger.info("Response-Status: $responseStatus")


            // 最后注意需要将 responseWrapper 的内容写入到原始 response
            // 最后一步执行，因为执行完后 ContentCachingResponseWrapper 的 FastByteArrayOutputStream 会被重置
            responseWrapper.copyBodyToResponse()

        }
    }

    private fun getRequestHeaderInfos(requestWrapper: HttpServletRequestWrapper): String {
        val requestHeaders = HttpHeaders()
        val headerNames = requestWrapper.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement() as String
            requestHeaders.add(headerName, requestWrapper.getHeader(headerName))
        }

        return requestHeaders.map {
            it.key + ":" + it.value.joinToString(",")
        }.joinToString("\n")
    }

}
