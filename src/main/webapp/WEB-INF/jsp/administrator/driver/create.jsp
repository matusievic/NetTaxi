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
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.repeat_password" var="repeatPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.car_model" var="carModelLabel"/>
    <fmt:message bundle="${loc}" key="account.tariff" var="tariffLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="backLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.administrator.drivers.create" var="createLabel"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.create" var="pageTitleLabel"/>

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
        <h3>${createLabel}</h3>
        <div class="content">
            <div class="row">
                <div class="col-100">
                    <c:out value="${error}"/>
                </div>
            </div>
            <form action="/controller?command=register_taxidriver" method="post">
                <div class="row">
                    <div class="col-25">
                        <label for="phone">${phoneLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="phone" name="phone" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="name">${nameLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="name" name="name" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="surname">${surnameLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="surname" name="surname" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="first-password">${passwordLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="password" class="input-field" id="first-password" name="first_password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="second-password">${repeatPasswordLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="password" class="input-field" id="second-password" name="second_password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="car_number">${carNumberLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="car_number" name="car_number" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="car_model">${carModelLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="car_model" name="car_model" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="tariff">${tariffLabel}</label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="input-field" id="tariff" name="tariff" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-100">
                        <div class="confirm-wrapper">
                            <input type="submit" value="OK">
                            <div class="cancel-button">
                                <a href="/controller?command=display_taxidrivers">${backLabel}</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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
