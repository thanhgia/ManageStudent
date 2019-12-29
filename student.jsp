<%-- 
    Document   : student
    Created on : Jul 11, 2019, 9:54:14 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USERNAME_STUDENT}">
            <font color="red">
            Welcome, ${sessionScope.USERNAME_STUDENT}
            </font>

            <a href="logout" style="float: right;">Logout</a>

            <h1>Student Page</h1>
            <h1>Access denied!!!</h1>
        </c:if>
        <c:if test="${empty sessionScope.USERNAME_STUDENT}">
            <jsp:forward page="login.html"/>
        </c:if>
    </body>
</html>
