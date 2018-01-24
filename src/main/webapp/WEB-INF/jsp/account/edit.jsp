<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="title.account" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.account.edit.about" var="aboutEditLabel"/>

    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.old_password" var="oldPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.password" var="firstPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.repeat_password" var="secondPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.tariff" var="tariffLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<header>
    <div class="wrapper">
        <a href="#" id="touch-navigation">Menu</a>
        <div class="logo">
            <a href="/">NetTaxi</a>
        </div>
        <nav>
            <ul class="menu">
                <taxi:menu></taxi:menu>
            </ul>
        </nav>
        <div class="banner">
            ${aboutLabel}
        </div>
    </div>
</header>
<main>
    <section>
        <h3>${aboutEditLabel}</h3>
        <p>

        <form action="/controller?command=update_account" method="post">
            <label for="name">${nameLabel}</label>
            <input type="text" name="name" id="name" value="${user.name}" required>

            <label for="surname">${surnameLabel}</label>
            <input type="text" name="surname" id="surname" value="${user.surname}" required>

            <label for="phone">${phoneLabel}</label>
            <input type="text" name="phone" id="phone" value="${user.phone}" required>

            <label for="old-password">${oldPasswordLabel}</label>
            <input type="password" name="old_password" id="old-password">

            <label for="first-password">${firstPasswordLabel}</label>
            <input type="password" name="first_password" id="first-password">

            <label for="second-password">${secondPasswordLabel}</label>
            <input type="password" name="second_password" id="second-password">

            <taxi:driver>
                <label for="car-number">${carNumberLabel}</label>
                <input type="text" name="car_number" value="${String.valueOf(user.car.number)}" id="car-number" required>

                <label for="car-model">${carModelLabel}</label>
                <input type="text" name="car_model" value="${user.car.model}" id="car-model" required>

                <label for="tariff">${tariffLabel}</label>
                <input type="text" name="tariff" value="${user.tariff}" id="tariff" required>
            </taxi:driver>

            <input type="submit" value="OK">
        </form>

        </p>
    </section>
</main>
<footer>
    <div class="company">
        NetTaxi 2018
        <%@include file="../include/language.jsp" %>
    </div>
    <div class="social">
        <a href="#"><img src="img/social/phone.png" alt=""></a>
        <a href="#"><img src="img/social/facebook.png" alt=""></a>
        <a href="#"><img src="img/social/twitter.png" alt=""></a>
        <a href="#"><img src="img/social/instagram.png" alt=""></a>
    </div>
</footer>
</body>
</html>
