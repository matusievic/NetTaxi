<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <%@include file="../include/header.jsp" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.sign_up" var="signupPageLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>

    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.repeat_password" var="repeatPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signupButtonLabel"/>

    <title>${signupPageLabel} - ${applicationNameLabel}</title>
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
        <h3>${signupButtonLabel}</h3>
        <div class="content">
            <c:out value="${error}"/>
            <form action="/controller" method="post">
                <input type="hidden" name="command" value="register_customer">
                <div class="row">
                    <div class="col-25">
                        <label for="phone-field">${phoneLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="phone-field" name="phone" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="name-filed">${nameLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="name-filed" name="name" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="surname-field">${surnameLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="surname-field" name="surname" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="first-password-field">${passwordLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="password" class="input-field" id="first-password-field" name="first_password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="second-password-field">${repeatPasswordLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="password" class="input-field" id="second-password-field" name="second_password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-100">
                        <input type="submit" value="OK">
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
