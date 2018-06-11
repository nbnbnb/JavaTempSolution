package sbwebapp.ext

import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.StandardCharsets.UTF_8
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by ZhangJin on 2018/6/12.
 */

class LoggerFilter : Filter {

    private val logger = LoggerFactory.getLogger(LoggerFilter::class.java)!!

    override fun init(filterConfig: FilterConfig?) {

    }

    override fun destroy() {

    }

    override fun doFilter(httpServletRequest: ServletRequest, httpServletResponse: ServletResponse, filterChain: FilterChain) {

        val requestWrapper = ContentCachingRequestWrapper(httpServletRequest as HttpServletRequest)
        val responseWrapper = ContentCachingResponseWrapper(httpServletResponse as HttpServletResponse)

        filterChain.doFilter(requestWrapper, responseWrapper)

        val requestUrl = requestWrapper.requestURL.toString()
        val requestHeaders = HttpHeaders()
        val headerNames = requestWrapper.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement() as String
            requestHeaders.add(headerName, requestWrapper.getHeader(headerName))
        }
        val httpMethod = HttpMethod.valueOf(requestWrapper.method)
        val requestParams = requestWrapper.parameterMap
        val requestBody = IOUtils.toString(requestWrapper.inputStream, UTF_8)
        val responseStatus = HttpStatus.valueOf(responseWrapper.statusCode)
        val responseHeaders = HttpHeaders()
        for (headerName in responseWrapper.headerNames) {
            responseHeaders.add(headerName, responseWrapper.getHeader(headerName))
        }
        val responseBody = IOUtils.toString(responseWrapper.contentInputStream, UTF_8)


        logger.info("ContentType: ${httpServletRequest.contentType}")
        logger.info("Method: $httpMethod")
        logger.info("responseBody: $responseBody")
        logger.info("requestBody: $requestBody")
        logger.info("responseStatus: $responseStatus")
        logger.info("requestParams: $requestParams")
        logger.info("requestUrl: $requestUrl")

        // 最后注意需要将 responseWrapper 的内容写入到原始 response
        // 最后一步执行，因为执行完后 ContentCachingResponseWrapper 的 FastByteArrayOutputStream 会被重置
        responseWrapper.copyBodyToResponse()
    }

}