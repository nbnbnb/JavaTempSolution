<%--
  Created by IntelliJ IDEA.
  User: ZhangJin
  Date: 2017/8/5
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>

<!--
contentType.charset 表示输出页面的编码，一般就是 UTF-8
pageEncoding 表示源代码编码，一般也是 UTF-8，不用进行显式设置

tomcat 默认的编码是 ISO-8859-1

contentType.charset 同时也表示提交数据的编码方式
当前页面是什么编码，POST 提交的数据也是什么编码

post 参数编码使用 request.charEncoding
get 参数编码使用 Parameters.queryStringEncoding

request.charEncoding 是通过 request.setCharacterEncoding("gbk"); 设定的

queryStringEncoding 通过 Connector 的 URIEncoding 进行设定，如下所示
<Connector port="8080" protocol="HTTP/1.1"
connectionTimeout="20000"
redirectPort="8443"
URIEncoding="UTF-8"
useBodyEncodingForURI="true" />
/>

默认情况下的 URL 编码为 UTF-8，例如，"测试输入发" 将会编码为 %E6%B5%8B%E8%AF%95%E8%BE%93%E5%85%A5%E5%8F%91
可以使用 decodeURI 或 decodeURIComponent 进行解码（Chrome 浏览器中显式的是解码后的字符）

"测试输入发" 的 UTF-8 编码为 E6-B5-8B-E8-AF-95-E8-BE-93-E5-85-A5-E5-8F-91，用 URL 编码表示则加 % 进行间隔
同时，request.getParameter("abc") 接收页面也默认使用 UTF-8 进行解码

如果在提交页面使用指定的 GBK 编码，则会编码为 %B2%E2%CA%D4%CA%E4%C8%EB%B7%A2
对于的 GBK 编码为 B2-E2-CA-D4-CA-E4-C8-EB-B7-A2

URL 的编解码与 charset 的设置无关

如果设置了 useBodyEncodingForURI = true ， 则 queryStringEncoding 会和 charEncoding 同步

在使用 POST 提交页面时，将会根据提交页面的 contentType.charset 对参数进行编码
POST 的参数也都使用 encodeURI 进行了编码
在接收页面，当使用 request.getParameter("abc") 进行获取参数值时，默认使用了 ISO-8859-1 对参数值进行解码
由于提交时候使用的是 UTF-8 进行编码，所以解码的时候肯定乱码
此处使用 new String(abc.getBytes("ISO-8859-1"), "utf8")，先还原到原始的 UTF-8 编码字节，再通过 UTF-8 解码输出

ISO-8859-1 = Latin-1
----------------------------------------------------------
useBodyEncodingForURI参数表示是对 GET 方式否用 request.setCharacterEncoding
在默认情况下，该参数为false。

URIEncoding参数指定对所有GET方式请求进行统一的重新编码（解码）的编码。

URIEncoding和useBodyEncodingForURI区别是，

URIEncoding是对所有GET方式的请求的数据进行统一的重新编码，

而useBodyEncodingForURI则是根据响应该请求的页面的request.setCharacterEncoding参数对数据进行的重新编码，不同的页面可以有不同的重新编码的编码

通过添加 Extention.Filter.SetCharacterEncodingFilter 过滤器解决所有问题（为所有的 POST/GET 解码设置为 UTF-8，不使用默认的 ISO-8859-1）
request.setCharacterEncoding(encoding);
chain.doFilter(request, response);
-->

<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Resutl Page</title>
</head>
<body>
<%
    // 可以在 Tomcat 读取默认编码之前进行显式设置
    // 这个方法只对 POST 方法生效
    // request.setCharacterEncoding("utf8");

    String abc = null;
    if (request != null) {
        // 如果是 get 方式传递参数
        // 此时的 abc 已经表示用 UTF-8 进行过解码
        // 如果传递方不是用 UTF-8 进行的编码，此处就会乱码
        abc = request.getParameter("abc");
    }
    if (abc == null) {
        out.println("空值");
    } else {
        out.println("原始编码：");
        out.println(abc);
        out.println("</br>");
        out.println("ISO-8859-1 to utf8 编码：");
        String abc1 = new String(abc.getBytes("ISO-8859-1"), "utf8");
        System.out.println(abc1);
        out.println(abc1);
        out.println("</br>");
        out.println("ISO-8859-1 to gbk 编码：");
        String abc2 = new String(abc.getBytes("ISO-8859-1"), "gbk");
        out.println(abc2);
    }
%>

</br>
</br>
</br>

<a href="input.jsp">返回</a>

</body>
</html>
