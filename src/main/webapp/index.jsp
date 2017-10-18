<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization/registration</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form id="myForm" method="post" action="/auth">
            <input type="text" placeholder="email" required value="" name="email"/>
            <input type="password" placeholder="password" required value="" name="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="${pageContext.servletContext.contextPath}/createUser">Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>

