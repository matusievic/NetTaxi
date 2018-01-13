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
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
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
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${drivers}">
        <tr>
            <td><a href="/controller?command=display_taxidriver&id=${customer.id}">${customer.id}</a></td>
            <td>${customer.phone}</td>
            <td>${customer.name}</td>
            <td>${customer.surname}</td>
            <td>${String.valueOf(customer.car.number)}</td>
            <td>${customer.car.model}</td>
            <td>${customer.banned}</td>
            <td>${customer.rating}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
