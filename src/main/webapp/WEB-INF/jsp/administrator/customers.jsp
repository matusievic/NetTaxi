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
    <fmt:message bundle="${loc}" key="account.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.discount" var="discountLabel"/>
    <fmt:message bundle="${loc}" key="title.customers" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

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
        <h3>${aboutDeleteLabel}</h3>
        <p>

        <table>
            <thead>
            <tr>
                <th>${idLabel}</th>
                <th>${phoneLabel}</th>
                <th>${nameLabel}</th>
                <th>${surnameLabel}</th>
                <th>${bannedLabel}</th>
                <th>${discountLabel}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="driver" items="${customers}">
                <tr>
                    <td><a href="/controller?command=display_customer&id=${driver.id}">${driver.id}</a></td>
                    <td>${driver.phone}</td>
                    <td>${driver.name}</td>
                    <td>${driver.surname}</td>
                    <td>${driver.banned}</td>
                    <td>${driver.discount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <taxi:pagination command="display_customers"/>

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
