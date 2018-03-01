<%--
  Created by IntelliJ IDEA.
  User: ZhangJin
  Date: 2017/8/5
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>

<!--

contentType="text/html;charset=UTF-8" 表示
POST 数据到服务器时，改如何解码
响应输出时，改如何编码（浏览器需要得到正确的编码信息，然后再进行相应的解码）

Tomcat 默认的编码是 ISO-8859-1（Latin-1），一般通过自定义 Filter 设置为 UTF-8，或者在 server.xml 进行全局设置

现代浏览器一般将 GET 参数通过 UTF-8 方式进行编码，Tomcat 可以通过 server.xml Connector 进行全局编码配置

<Connector port="8080" protocol="HTTP/1.1"
connectionTimeout="20000"
redirectPort="8443"
URIEncoding="UTF-8"
useBodyEncodingForURI="true" />

POST 的编码通过 request.getCharacterEncoding() 读取

-->

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Charsets Page</title>
</head>
<body>

<a href="input.jsp">返回</a>

</br>
</br>
</br>

<%

    if (request != null) {
        // 如果是 get 方式传递参数
        // 此时的 input 已经表示用 UTF-8 进行过解码
        // 如果传递方式不是用 UTF-8 进行的编码，此处就会乱码
        String input = request.getParameter("input");

        if(input!=null) {
            out.println("请求方式：");
            out.println(request.getMethod());
            out.println("</br>");
            out.println("原始编码：");
            // 使用了 SetCharacterEncodingFilter 过滤器
            // 所以默认都使用了 UTF-8 方式进行解码
            out.println(input);
            out.println("</br>");
            out.println("ISO-8859-1（Latin-1） to utf8 编码：");
            String tp = new String(input.getBytes("ISO-8859-1"), "utf8");
            out.println(tp);
            out.println("</br>");
            out.println("ISO-8859-1（Latin-1） to gbk 编码：");
            tp = new String(input.getBytes("ISO-8859-1"), "gbk");
            out.println(tp);
        }
    }else {
        out.println("request == null");
    }

%>


</body>
</html>
