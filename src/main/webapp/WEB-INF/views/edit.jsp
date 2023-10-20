<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td><input type='hidden' name='id' value="<c:out value="${post.id}"/>"></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type='text' name='name' value="<c:out value="${post.name}"/>"></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type='text' name='desc' value="<c:out value="${post.desc}"/>"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>