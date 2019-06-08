<%@ page import="ru.javawebinar.topjava.util.DatesUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
        <c:choose>
            <c:when test="${mealTo.excess == true}">
                <tr bgcolor="red">
                    <td><%= DatesUtils.format(mealTo.getDateTime())%></td>
                    <td>${mealTo.description}</td>
                    <td>${mealTo.calories}</td>
                    <td><img src="img/pencil.png"></td>
                    <td><img src="img/delete.png"></td>
                </tr>
            </c:when>
            <c:when test="${mealTo.excess == false}">
                <tr bgcolor="#adff2f">
                    <td><%= DatesUtils.format(mealTo.getDateTime())%></td>
                    <td>${mealTo.description}</td>
                    <td>${mealTo.calories}</td>
                    <td><img src="img/pencil.png"></td>
                    <td><img src="img/delete.png"></td>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
</table>