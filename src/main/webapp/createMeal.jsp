<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUser</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/createMeal" method="POST">
    <table>
        <tr>
            <td align="right">Время приема еды :</td>
            <td>
                <input type="datetime-local" required value="" name="dateTime">
            </td>
        </tr>
        <tr>
            <td align="right">Наименование приема еды :</td>
            <td>
                <input type="text" required value="" name="description">
            </td>
        </tr>
        <tr>
            <td align="right">Количество калорий в еде :</td>
            <td>
                <input type="text" required value="" name="calories">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Создать"/></td>
        </tr>
    </table>
</form>
</body>
</html>