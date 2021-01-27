<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 0004
  Time: 16:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>添加数据</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/addstu" method="get">
    name:<input type="text" name="name"><br>
    age:<input type="text" name="age"><br>
    info:<input type="text" name="info"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
