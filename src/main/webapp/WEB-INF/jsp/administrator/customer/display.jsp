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
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.discount" var="discountLabel"/>
    <fmt:message bundle="${loc}" key="activity.block" var="blockLabel"/>
    <fmt:message bundle="${loc}" key="activity.unblock" var="unblockLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="backLabel"/>
    <fmt:message bundle="${loc}" key="title.customer" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.administrator.customers.customer" var="customerLabel"/>

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
        <h3>${customerLabel}</h3>
        <div class="content">
            <table class="info-table">
                <tr>
                    <th>${idLabel}</th>
                    <td>${customer.id}</td>
                </tr>
                <tr>
                    <th>${nameLabel}</th>
                    <td>${customer.name}</td>
                </tr>
                <tr>
                    <th>${surnameLabel}</th>
                    <td>${customer.surname}</td>
                </tr>
                <tr>
                    <th>${phoneLabel}</th>
                    <td>${customer.phone}</td>
                </tr>
                <tr>
                    <th>${bannedLabel}</th>
                    <td>${customer.banned}</td>
                </tr>
                <tr>
                    <th>${discountLabel}</th>
                    <td>${customer.discount}</td>
                </tr>
                <taxi:admin>
                    <form action="/controller?command=discount_customer&id=${customer.id}" method="post">
                        <tr>
                            <td>
                                <input type="text" class="input-field" name="discount" id="discount"
                                       placeholder="${discountLabel}">
                            </td>
                            <td>
                                <input type="submit" value="OK">
                            </td>
                        </tr>
                    </form>
                </taxi:admin>
            </table>
            <taxi:admin>
                <c:choose>
                    <c:when test="${!customer.banned}">
                        <a href="/controller?command=block_customer&id=${customer.id}">${blockLabel}</a>
                    </c:when>
                    <c:when test="${customer.banned}">
                        <a href="/controller?command=unblock_customer&id=${customer.id}">${unblockLabel}</a>
                    </c:when>
                </c:choose>
                <a href="/controller?command=display_customers">${backLabel}</a>
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
