<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.orders" var="pageTitleLabel"/>

    <fmt:message bundle="${loc}" key="order.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="order.price" var="priceLabel"/>
    <fmt:message bundle="${loc}" key="order.begin" var="beginLabel"/>
    <fmt:message bundle="${loc}" key="order.end" var="endLabel"/>
    <fmt:message bundle="${loc}" key="order.customer" var="customerLabel"/>
    <fmt:message bundle="${loc}" key="order.driver" var="driverLabel"/>
    <fmt:message bundle="${loc}" key="order.status" var="statusLabel"/>
    <fmt:message bundle="${loc}" key="order.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="activity.cancel" var="cancelLabel"/>

    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>

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
            <input type="hidden" id="cancel-label" value="${cancelLabel}">
            <taxi:customer>
            <script type="text/javascript" src="js/customer_orders.js"></script>
        <div id="active-customer-order">
            <table id="active-customer-order-table">
                <thead>
                <tr>
                    <th>${idLabel}</th>
                    <th>${nameLabel}</th>
                    <th>${surnameLabel}</th>
                    <th>${phoneLabel}</th>
                    <th>${carModelLabel}</th>
                    <th>${carNumberLabel}</th>
                    <th>${beginLabel}</th>
                    <th>${endLabel}</th>
                    <th>${priceLabel}</th>
                    <th>${statusLabel}</th>
                    <th>${ratingLabel}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        </taxi:customer>
        <taxi:driver>
            <script type="text/javascript" src="js/driver_orders.js"></script>
            <div id="active-driver-order">
                <table id="active-driver-order-table">
                    <thead>
                    <tr>
                        <th>${idLabel}</th>
                        <th>${customerLabel}</th>
                        <th>${beginLabel}</th>
                        <th>${endLabel}</th>
                        <th>${priceLabel}</th>
                        <th>${statusLabel}</th>
                        <th>${ratingLabel}</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </taxi:driver>
        <table>
            <thead>
            <tr>
                <th>${idLabel}</th>
                <th>${customerLabel}</th>
                <th>${driverLabel}</th>
                <th>${beginLabel}</th>
                <th>${endLabel}</th>
                <th>${priceLabel}</th>
                <th>${statusLabel}</th>
                <th>${ratingLabel}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><a href="/controller?command=display_order&id=${order.id}">${order.id}</a></td>
                    <td><a href="/controller?command=display_customer&id=${order.customerId}">${order.customerId}</a></td>
                    <td><a href="/controller?command=display_taxidriver&id=${order.taxiDriverId}">${order.taxiDriverId}</a></td>
                    <td>${order.begin.x}, ${order.begin.y}</td>
                    <td>${order.end.x}, ${order.end.y}</td>
                    <td>${order.price}</td>
                    <td>${order.status}</td>
                    <td>${order.rating}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <taxi:pagination command="display_orders"/>

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
