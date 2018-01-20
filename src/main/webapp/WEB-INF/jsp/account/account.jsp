<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="title.account" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="account.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.discount" var="discountLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="account.tariff" var="tariffLabel"/>

    <fmt:message bundle="${loc}" key="activity.edit" var="editLabel"/>
    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<a href="/controller?command=display_taxidrivers">Drivers</a>
<a href="/controller?command=display_customers">Customers</a>
<a href="/controller?command=display_orders">Orders</a>
<a href="/controller?command=display_order_taxi_page">Orders</a>
<table>
    <tr>
        <th>${idLabel}</th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th>${nameLabel}</th>
        <td>${user.name}</td>
    </tr>
    <tr>
        <th>${surnameLabel}</th>
        <td>${user.surname}</td>
    </tr>
    <tr>
        <th>${phoneLabel}</th>
        <td>${user.phone}</td>
    </tr>
    <tr>
        <th>${passwordLabel}</th>
        <td>******</td>
    </tr>
    <taxi:customer>
        <tr>
            <th>${discountLabel}</th>
            <td>${user.discount}</td>
        </tr>
    </taxi:customer>
    <taxi:driver>
        <tr>
            <th>${carNumberLabel}</th>
            <td>${String.valueOf(user.car.number)}</td>
        </tr>
        <tr>
            <th>${carModelLabel}</th>
            <td>${user.car.model}</td>
        </tr>
        <tr>
            <th>${ratingLabel}</th>
            <td>${user.rating}</td>
        </tr>
        <tr>
            <th>${tariffLabel}</th>
            <td>${user.tariff}</td>
        </tr>
    </taxi:driver>
</table>
<a href="/account/edit">${editLabel}</a>
<a href="/account/delete">${deleteLabel}</a>
</body>
</html>
