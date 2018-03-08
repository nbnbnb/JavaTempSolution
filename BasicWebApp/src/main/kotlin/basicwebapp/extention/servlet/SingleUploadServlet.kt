package basicwebapp.extention.servlet

import javax.servlet.ServletException
import javax.servlet.annotation.MultipartConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Part
import java.io.IOException

/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebServlet(urlPatterns = arrayOf("/singleUpload"))
@MultipartConfig
class SingleUploadServlet : HttpServlet() {

    private fun getFileName(part: Part): String? {
        val contentDispositionHeader = part.getHeader("content-disposition")
        val elements = contentDispositionHeader.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (element in elements) {
            if (element.trim({ it <= ' ' }).startsWith("filename")) {
                return element.substring(element.lastIndexOf('=') + 1).trim({ it <= ' ' }).replace("\"", "")
            }
        }

        return null
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.sendRedirect("singleUpload.jsp")
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {

        // save uploaded file to WEB-INF
        val part = req.getPart("binaryFile")
        // 文件名需要在 content-disposition 域中获取

        val fileName = getFileName(part)
        if (fileName != null && fileName.length != 0) {
            part.write(servletContext.getRealPath("/WEB-INF") + "/" + fileName)
        }

        // write to browser
        resp.contentType = "text/html;charset=UTF-8"
        val writer = resp.writer
        writer.print("<br/>Upload file name: " + fileName!!)
        writer.print("<br/>Size: " + part.size)
        writer.print("<br/>Author: " + req.getParameter("author"))

    }

    companion object {
        private val serialVersionUID = 8593038L
    }
}
