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

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="order.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="order.price" var="priceLabel"/>
    <fmt:message bundle="${loc}" key="order.begin" var="beginLabel"/>
    <fmt:message bundle="${loc}" key="order.end" var="endLabel"/>
    <fmt:message bundle="${loc}" key="order.customer" var="customerLabel"/>
    <fmt:message bundle="${loc}" key="order.driver" var="driverLabel"/>
    <fmt:message bundle="${loc}" key="order.status" var="statusLabel"/>
    <fmt:message bundle="${loc}" key="order.rating" var="ratingLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.common.orders.order" var="orderLabel"/>

    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>
    <fmt:message bundle="${loc}" key="activity.rate" var="rateLabel"/>

    <title>#${order.id} - ${applicationNameLabel}</title>
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
        <h3>${orderLabel}</h3>
        <div class="content">
        <table class="info-table">
            <tr>
                <th>${idLabel}</th>
                <td>${order.id}</td>
            </tr>
            <tr>
                <th>${customerLabel}</th>
                <td><a href="/controller?command=display_customer&id=${order.customerId}">${order.customerId}</a></td>
            </tr>
            <tr>
                <th>${driverLabel}</th>
                <td><a href="/controller?command=display_taxidriver&id=${order.taxiDriverId}">${order.taxiDriverId}</a></td>
            </tr>
            <tr>
                <th>${beginLabel}</th>
                <td>${order.begin.x}, ${order.begin.y}</td>
            </tr>
            <tr>
                <th>${endLabel}</th>
                <td>${order.end.x}, ${order.end.y}</td>
            </tr>
            <tr>
                <th>${priceLabel}</th>
                <td>${order.price}</td>
            </tr>
            <tr>
                <th>${statusLabel}</th>
                <td>${order.status}</td>
            </tr>
            <tr>
                <th>${ratingLabel}</th>
                <td>${order.rating}</td>
            </tr>
            <taxi:customer>
                <c:if test="${order.rating == 0}">
                    <tr>
                        <th>${rateLabel}</th>
                        <td>
                            <div class="rating">
                                <span><a href="/controller?command=rate_order&id=${order.id}&rating=1">☆</a></span>
                                <span><a href="/controller?command=rate_order&id=${order.id}&rating=2">☆</a></span>
                                <span><a href="/controller?command=rate_order&id=${order.id}&rating=3">☆</a></span>
                                <span><a href="/controller?command=rate_order&id=${order.id}&rating=4">☆</a></span>
                                <span><a href="/controller?command=rate_order&id=${order.id}&rating=5">☆</a></span>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </taxi:customer>
        </table>
        <taxi:admin>
            <a href="/controller?command=delete_order&id=${order.id}">${deleteLabel}</a>
        </taxi:admin>
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
