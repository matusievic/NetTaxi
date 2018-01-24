<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    <script type="text/javascript" src="https://api-maps.yandex.ru/2.1/?lang=en_US"></script>
    <script type="text/javascript" src="/js/order.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.order" var="pageTitleLabel"/>

    <fmt:message bundle="${loc}" key="account.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>

    <fmt:message bundle="${loc}" key="order.length" var="lengthLabel"/>
    <fmt:message bundle="${loc}" key="order.price" var="priceLabel"/>

    <fmt:message bundle="${loc}" key="activity.choose" var="chooseLabel"/>

    <fmt:message bundle="${loc}" key="account.sign_in" var="signInLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signUpLabel"/>

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
        <h3></h3>
        <p>
        <form action="/controller?command=order">
        </form>
        <div id="map"></div>
        <div id="cost"></div>
        <div id="drivers">
            <table id="drivers-table">
                <thead>
                <tr>
                    <th>${idLabel}</th>
                    <th>${nameLabel}</th>
                    <th>${surnameLabel}</th>
                    <th>${phoneLabel}</th>
                    <th>${carModelLabel}</th>
                    <th>${carNumberLabel}</th>
                    <th>${ratingLabel}</th>
                    <th>${lengthLabel}</th>
                    <th>${priceLabel}</th>
                    <th>${chooseLabel}</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

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
