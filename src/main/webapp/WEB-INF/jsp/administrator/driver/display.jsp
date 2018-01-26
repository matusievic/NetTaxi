<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../include/header.jsp" %>
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


    <fmt:message bundle="${loc}" key="content.administrator.drivers.driver" var="driverLabel"/>
    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.display" var="pageTitleLabel"/>

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
        <h3>${driverLabel}</h3>
        <div class="content">
            <table class="info-table">
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
        </div>
    </section>
</main>
<footer>
    <div class="company">
        NetTaxi 2018
        <%@include file="../../include/language.jsp" %>
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
