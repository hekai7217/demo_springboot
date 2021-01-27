<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 0004
  Time: 16:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%-- 引入jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>展示数据</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/addstu.jsp">添加学生</a>
所有的学生 <br>
<table cellspacing="0" border="1">

    <tr>
        <td>id</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>说明</td>
    </tr>

    <c:forEach var="stu" items="${stus}">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.info}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
