<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="/css/signin.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.sign_in" var="pageTitleLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>

    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_in" var="signinButtonLabel"/>

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
                <nav>
                    <ul class="menu">
                        <taxi:menu></taxi:menu>
                    </ul>
                </nav>
            </ul>
        </nav>
    </div>
</header>
<main>
    <div class="sign-in">
        <div class="sign-in-header">
            <h1>${pageTitleLabel}</h1>
        </div>
        <div class="sign-in-form">
            <form action="/controller" method="get">
                <input type="hidden" name="command" value="authenticate">
                <h3>${phoneLabel}:</h3>
                <input name="phone" type="text" placeholder="${phoneLabel}"/><br>
                <h3>${passwordLabel}:</h3>
                <input name="password" type="password" placeholder="${passwordLabel}"/>
                <br>
                <input type="submit" value="${signinButtonLabel}" class="sign-in-button"/>
            </form>
        </div>
    </div>
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
