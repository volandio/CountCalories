
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUser</title>
    <link rel="stylesheet" href="css/login.css">
<body>
<div class="create-page">
    <div class="form">
            <form id="myForm" method="post" action="${pageContext.servletContext.contextPath}/createUser">
            <input type="text" placeholder="name" name="name"/>
            <input type="password" placeholder="password" name="password"/>
            <input type="text" placeholder="email address" name="email"/>
            <button>create</button>
        </form>
    </div>
</div>
</body>
</html>
