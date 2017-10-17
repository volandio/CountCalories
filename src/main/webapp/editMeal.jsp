<%@ page contentType="text/html;charset=cp1251" language="java" %>
<html>
<head>
    <title>EditMeal</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/editMeal" method="POST">
    <input type="hidden" name="id" value="${meal.mealId}">
    <table>
        <tr>
            <td align="right">����� ������ ��� :</td>
            <td>
                <input type="text" name="dateTime" value="${meal.dateTime}">
            </td>
        </tr>
        <tr>
            <td align="right">������������ ������ ��� :</td>
            <td>
                <input type="text" name="description" value="${meal.description}">
            </td>
        </tr>
        <tr>
            <td align="right">���������� ������� � ��� :</td>
            <td>
                <input type="text" name="calories" value="${meal.calories}">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="��������"/></td>
        </tr>
    </table>
</form>
</body>
</html>
