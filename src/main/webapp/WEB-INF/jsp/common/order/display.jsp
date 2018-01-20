<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="order.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="order.price" var="priceLabel"/>
    <fmt:message bundle="${loc}" key="order.begin" var="beginLabel"/>
    <fmt:message bundle="${loc}" key="order.end" var="endLabel"/>
    <fmt:message bundle="${loc}" key="order.customer" var="customerLabel"/>
    <fmt:message bundle="${loc}" key="order.driver" var="driverLabel"/>
    <fmt:message bundle="${loc}" key="order.status" var="statusLabel"/>
    <fmt:message bundle="${loc}" key="order.rating" var="ratingLabel"/>

    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>

    <title>#${order.id} - ${applicationNameLabel}</title>
</head>
<body>
<table>
    <tr>
        <th>${idLabel}</th>
        <td>${order.id}</td>
    </tr>
    <tr>
        <th>${customerLabel}</th>
        <td><a href="/controller?command=display_customer&id=${order.customerId}">${order.customerId}</a></td>
    </tr>
    <tr>
        <th>${driverLabel}</th>
        <td><a href="/controller?command=display_taxidriver&id=${order.taxiDriverId}">${order.taxiDriverId}</a></td>
    </tr>
    <tr>
        <th>${beginLabel}</th>
        <td>${order.begin.x}, ${order.begin.y}</td>
    </tr>
    <tr>
        <th>${endLabel}</th>
        <td>${order.end.x}, ${order.end.y}</td>
    </tr>
    <tr>
        <th>${priceLabel}</th>
        <td>${order.price}</td>
    </tr>
    <tr>
        <th>${statusLabel}</th>
        <td>${order.status}</td>
    </tr>
    <tr>
        <th>${ratingLabel}</th>
        <td>${order.rating}</td>
    </tr>
</table>
<taxi:admin>
    <a href="/controller?command=delete_order&id=${order.id}">${deleteLabel}</a>
</taxi:admin>
<taxi:customer>
    <c:if test="${order.rating == 0}">
        <form action="/controller?command=rate_order&id=${order.id}" method="post">
            <label for="rating">${ratingLabel}</label>
            <input type="text" name="rating" id="rating" required>
            <input type="submit" value="OK">
        </form>
    </c:if>
</taxi:customer>
</body>
</html>
