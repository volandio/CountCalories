<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUser</title>
    <link rel="stylesheet" href="css/login.css">
<body>
<div class="create-page">
    <div class="form">
        <form id="myForm" method="post" action="${pageContext.servletContext.contextPath}/createUser">
            <input type="text" placeholder="name" required value="" name="name"/>
            <input type="password" placeholder="password" required value="" name="password"/>
            <input type="text" placeholder="email address" required value="" name="email"/>
            <label style="color:red;  white-space: nowrap;"><c:out value="${message}"></c:out></label>
            <button>create</button>
        </form>
    </div>
</div>
</body>
</html>
