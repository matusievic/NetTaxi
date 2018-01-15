<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
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
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.free" var="freeLabel"/>
    <fmt:message bundle="${loc}" key="account.location" var="locationLabel"/>
    <fmt:message bundle="${loc}" key="title.drivers" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="activity.create" var="createButtonLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<a href="/administrator/driver/create">${createButtonLabel}</a>
<table>
    <thead>
    <tr>
        <th>${idLabel}</th>
        <th>${phoneLabel}</th>
        <th>${nameLabel}</th>
        <th>${surnameLabel}</th>
        <th>${carNumberLabel}</th>
        <th>${carModelLabel}</th>
        <th>${bannedLabel}</th>
        <th>${ratingLabel}</th>
        <th>${freeLabel}</th>
        <th>${locationLabel}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td><a href="/controller?command=display_taxidriver&id=${driver.id}">${driver.id}</a></td>
            <td>${driver.phone}</td>
            <td>${driver.name}</td>
            <td>${driver.surname}</td>
            <td>${String.valueOf(driver.car.number)}</td>
            <td>${driver.car.model}</td>
            <td>${driver.banned}</td>
            <td>${driver.rating}</td>
            <td>${driver.free}</td>
            <td>${driver.location.x} , ${driver.location.y}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<taxi:pagination command="display_taxidrivers"/>
</body>
</html>
