<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    <script type="text/javascript" src="js/order.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.order" var="pageTitleLabel"/>


    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>

    <fmt:message bundle="${loc}" key="account.sign_in" var="signinPageLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signupPageLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="../include/language.jsp"/>
<form action="/controller?command=order">
</form>
<div id="map"></div>
<div id="cost"></div>
<div id="drivers">
    <table id="drivers-table">
        <thead>
        <tr>
            <th>${nameLabel}</th>
            <th>${surnameLabel}</th>
            <th>${phoneLabel}</th>
            <th>${carModelLabel}</th>
            <th>${carNumberLabel}</th>
            <th>${ratingLabel}</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
