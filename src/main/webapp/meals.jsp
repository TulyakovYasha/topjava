<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        dt {
            float: left; /* Для размещения на одной строке */
            width: 100px; /* Ширина для текста */
            text-align: right; /* Выравнивание по правому краю */
            padding-right: 5px; /* Отступ справа */
            min-height: 1px; /* Минимальная высота */
        }

        dd {
            position: relative; /* Относительное позиционирование */
            top: -1px; /* Смещаем поля вверх */
            margin-bottom: 10px; /* Расстояние между строк */
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <form method="get">
        <input type="hidden" name="action" value="doFilter">
        <dl>
            <dt>От Даты</dt>
            <dd><input type="date" name="startDate"></dd>
            <dt>До Даты</dt>
            <dd><input type="date" name="endDate"></dd>
            <dt>От Времени</dt>
            <dd><input type="time" name="startTime"></dd>
            <dt>До Времени</dt>
            <dd><input type="time" name="endTime"></dd>
        </dl>
        <p><input type="reset" value="Отмена">
            <input type="submit" value="Отфильтровать"></p>
    </form>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>