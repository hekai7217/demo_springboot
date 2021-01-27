<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 0019
  Time: 15:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   demo01 jsp page
   <br>
<%-- maven 项目中 el表达式不不能识别
        解决 : isELIgnored="false"  告诉jsp 页面 不能忽略 el表达式
--%>
${name1} <br>
${name2} <br>

</body>
</html>
