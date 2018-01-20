<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="account.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="account.free" var="freeLabel"/>
    <fmt:message bundle="${loc}" key="account.location" var="locationLabel"/>
    <fmt:message bundle="${loc}" key="account.tariff" var="tariffLabel"/>

    <fmt:message bundle="${loc}" key="activity.block" var="blockLabel"/>
    <fmt:message bundle="${loc}" key="activity.unblock" var="unblockLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="backLabel"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.display" var="pageTitleLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<table>
    <tr>
        <th>${idLabel}</th>
        <td>${taxiDriver.id}</td>
    </tr>
    <tr>
        <th>${nameLabel}</th>
        <td>${taxiDriver.name}</td>
    </tr>
    <tr>
        <th>${surnameLabel}</th>
        <td>${taxiDriver.surname}</td>
    </tr>
    <tr>
        <th>${phoneLabel}</th>
        <td>${taxiDriver.phone}</td>
    </tr>
    <tr>
        <th>${carNumberLabel}</th>
        <td>${String.valueOf(taxiDriver.car.number)}</td>
    </tr>
    <tr>
        <th>${carModelLabel}</th>
        <td>${taxiDriver.car.model}</td>
    </tr>
    <tr>
        <th>${bannedLabel}</th>
        <td>${taxiDriver.banned}</td>
    </tr>
    <tr>
        <th>${ratingLabel}</th>
        <td>${taxiDriver.rating}</td>
    </tr>
    <tr>
        <th>${freeLabel}</th>
        <td>${taxiDriver.free}</td>
    </tr>
    <tr>
        <th>${locationLabel}</th>
        <td>${taxiDriver.location.x}, ${taxiDriver.location.y}</td>
    </tr>
    <tr>
        <th>${tariffLabel}</th>
        <td>${taxiDriver.tariff}</td>
    </tr>
</table>
<taxi:admin>
    <c:choose>
        <c:when test="${!taxiDriver.banned}">
            <a href="/controller?command=block_taxidriver&id=${taxiDriver.id}">${blockLabel}</a>
        </c:when>
        <c:when test="${taxiDriver.banned}">
            <a href="/controller?command=unblock_taxidriver&id=${taxiDriver.id}">${unblockLabel}</a>
        </c:when>
    </c:choose>
</taxi:admin>
<a href="/controller?command=display_taxidrivers">${backLabel}</a>
</body>
</html>
