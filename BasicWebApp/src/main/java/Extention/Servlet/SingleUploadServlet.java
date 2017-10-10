package Extention.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZhangJin on 2017/7/15.
 */
@WebServlet(urlPatterns = {"/singleUpload"})
@MultipartConfig
public class SingleUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 8593038L;

    private String getFileName(Part part) {
        String contentDispositionHeader =
                part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.lastIndexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // save uploaded file to WEB-INF
        Part part = req.getPart("filename");
        // 文件名需要在 content-disposition 域中获取
        String fileName = getFileName(part);
        if (fileName != null && fileName.length() != 0) {
            part.write(getServletContext().getRealPath("/WEB-INF") + "/" + fileName);
        }

        // write to browser
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("<br/>Upload file name: " + fileName);
        writer.print("<br/>Size: " + part.getSize());


        String author = req.getParameter("author");
        writer.print("<br/>Autho: " + author);

    }
}
