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

    <fmt:message bundle="${loc}" key="title.delete" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.account.delete.about" var="aboutDeleteLabel"/>

    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="activity.sure" var="sureLabel"/>
    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>

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
        <div class="content">
            <form action="/controller?command=delete_account" method="post">
                <div class="row">
                    <div class="col-25">
                        <label for="password">${passwordLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="password" class="input-field" name="password" id="password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="sure-box">${sureLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="checkbox" class="input-field" id="sure-box" onchange="document.getElementById('delete-button').disabled = !this.checked;" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-100">
                        <input type="submit" id="delete-button" disabled="true" value="${deleteLabel}">
                    </div>
                </div>
            </form>
        </div>
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
