<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Users list</h2>
<c:forEach items="${users}" var="user" >
    <tr>
        <td>${user.getId() }</td>
        <td>${user.getName() }</td>
        <td>${user.getAge() }</td>
    </tr>
</c:forEach>
</ul>
</body>
</html>
