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
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.repeat_password" var="repeatPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.tariff" var="tariffLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="backLabel"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.create" var="pageTitleLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<c:out value="${error}"/>
<form action="/controller?command=register_taxidriver" method="post">
    <input type="text" name="phone" placeholder="${phoneLabel}" required>
    <input type="text" name="name" placeholder="${nameLabel}" required>
    <input type="text" name="surname" placeholder="${surnameLabel}" required>
    <input type="password" name="first_password" placeholder="${passwordLabel}" required>
    <input type="password" name="second_password" placeholder="${repeatPasswordLabel}" required>
    <input type="text" name="car_number" placeholder="${carModelLabel}" required>
    <input type="text" name="car_model" placeholder="${carModelLabel}" required>
    <input type="text" name="tariff" placeholder="${tariffLabel}" required>
    <input type="submit" value="OK">
</form>
<a href="/controller?command=display_taxidrivers">${backLabel}</a>
</body>
</html>
