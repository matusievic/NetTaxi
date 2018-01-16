<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.orders" var="pageTitleLabel"/>

    <fmt:message bundle="${loc}" key="order.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="order.price" var="priceLabel"/>
    <fmt:message bundle="${loc}" key="order.begin" var="beginLabel"/>
    <fmt:message bundle="${loc}" key="order.end" var="endLabel"/>
    <fmt:message bundle="${loc}" key="order.cutomer" var="customerLabel"/>
    <fmt:message bundle="${loc}" key="order.driver" var="driverLabel"/>
    <fmt:message bundle="${loc}" key="order.status" var="statusLabel"/>
    <fmt:message bundle="${loc}" key="order.rating" var="ratingLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>${idLabel}</th>
        <th>${customerLabel}</th>
        <th>${driverLabel}</th>
        <th>${beginLabel}</th>
        <th>${endLabel}</th>
        <th>${priceLabel}</th>
        <th>${statusLabel}</th>
        <th>${ratingLabel}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td><a href="/controller?command=display_order&id=${order.id}">${order.id}</a></td>
            <td><a href="/controller?command=display_customer&id=${order.customerId}">${order.customerId}</a></td>
            <td><a href="/controller?command=display_taxidriver&id=${order.taxiDriverId}">${order.taxiDriverId}</a></td>
            <td>${order.begin.x}, ${order.begin.y}</td>
            <td>${order.end.x}, ${order.end.y}</td>
            <td>${order.price}</td>
            <td>${order.status}</td>
            <td>${order.rating}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<taxi:pagination command="display_orders"/>
</body>
</html>
