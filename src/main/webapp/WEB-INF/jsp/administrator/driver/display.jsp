<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="user.taxi_driver.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.driver.display.ban" var="blockLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.driver.display.unban" var="unblockLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.driver.display.back" var="backLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.driver.display.title" var="pageTitleLabel"/>

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
</table>
<c:choose>
    <c:when test="${!taxiDriver.banned}">
        <a href="/controller?command=block_taxidriver&id=${taxiDriver.id}">${blockLabel}</a>
    </c:when>
    <c:when test="${taxiDriver.banned}">
        <a href="/controller?command=unblock_taxidriver&id=${taxiDriver.id}">${unblockLabel}</a>
    </c:when>
</c:choose>
<a href="/controller?command=display_taxidrivers">${backLabel}</a>
</body>
</html>
