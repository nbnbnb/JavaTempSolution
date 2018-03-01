<%--
  Created by IntelliJ IDEA.
  User: ZhangJin
  Date: 2017/7/15
  Time: 16:48
  To change this template use File | Settings | File Templates.

  在 Tomcat conf tomcat-users.xml 目录中配置用户信息

  例如
     <role rolename="manager" />
     <role rolename="member" />
     <user username="tom" password="secret" roles="manager,member" />
     <user username="jerry" password="secret" roles="member" />

--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Secret Page</title>
</head>
<body>
    <h3>Haha，This is a Secret Page!</h3>
</body>
</html>
