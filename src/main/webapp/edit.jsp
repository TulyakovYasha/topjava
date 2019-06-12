<%@ page import="ru.javawebinar.topjava.util.DatesUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo" scope="request"/>
    <title>Meal</title>
</head>
<section>
    <form method="post" action="meals">
        <input type="hidden" name="excess" value="${mealTo.excess}">
        <input type="hidden" name="id" value="${mealTo.id}">
        <p><b>Дата/Время</b><br>
            <input type="datetime-local" name="time" size="50" value="<%=DatesUtils.format(mealTo.getDateTime())%>">
        <p><b>Описание</b></p>
        <input type="text" name="description" size="25" value="${mealTo.description}">
        <p><b>Калории</b></p>
        <input type="text" name="calories" size="25" value="${mealTo.calories}">
        <p>
            <button type="submit">Сохранить</button>
            <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</html>