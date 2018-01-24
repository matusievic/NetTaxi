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

    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>

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
        <h3></h3>
        <p>
        <table>
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
        </table>
        <taxi:admin>
            <a href="/controller?command=delete_order&id=${order.id}">${deleteLabel}</a>
        </taxi:admin>
        <taxi:customer>
            <c:if test="${order.rating == 0}">
                <form action="/controller?command=rate_order&id=${order.id}" method="post">
                    <label for="rating">${ratingLabel}</label>
                    <input type="text" name="rating" id="rating" required>
                    <input type="submit" value="OK">
                </form>
            </c:if>
        </taxi:customer>
        </p>
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
