<%@ page import="ru.javawebinar.topjava.util.DatesUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список еды</title>
</head>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="mealTo">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr bgcolor="${mealTo.excess ? "red" : "green"}">
            <td><%= DatesUtils.format(mealTo.getDateTime())%>
            </td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?id=${mealTo.id}&action=edit"><img src="img/pencil.png"></a></td>
            <td><a href="meals?id=${mealTo.id}&action=delete"><img src="img/delete.png"></a></td>
        </tr>
    </c:forEach>
</table>