<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Profile Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>name</th><th>login</th>
            </tr>
        <c:forEach var="user" items="${requestScope.userdto}">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
            </tr>
        </c:forEach>
        </table>
        <hr>
        <a href="./logout">Logout -></a>
    </body>
</html>




