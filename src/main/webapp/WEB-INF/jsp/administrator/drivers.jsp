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
    <fmt:message bundle="${loc}" key="account.car_number" var="carNumberLabel"/>
    <fmt:message bundle="${loc}" key="account.rating" var="ratingLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.free" var="freeLabel"/>

    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>
    <fmt:message bundle="${loc}" key="content.administrator.drivers.about" var="driversLabel"/>

    <fmt:message bundle="${loc}" key="activity.create" var="createButtonLabel"/>
    <fmt:message bundle="${loc}" key="title.drivers" var="pageTitleLabel"/>
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
        <h3>${driversLabel}</h3>
        <div class="content">
            <a href="/administrator/driver/create">${createButtonLabel}</a><br><br>
            <table class="resp-table">
                <thead>
                <tr>
                    <th>${idLabel}</th>
                    <th>${phoneLabel}</th>
                    <th>${nameLabel}</th>
                    <th>${surnameLabel}</th>
                    <th>${carNumberLabel}</th>
                    <th>${bannedLabel}</th>
                    <th>${ratingLabel}</th>
                    <th>${freeLabel}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="driver" items="${drivers}">
                    <tr>
                        <td data-label="${idLabel}"><a href="/controller?command=display_taxidriver&id=${driver.id}">${driver.id}</a></td>
                        <td data-label="${phoneLabel}">${driver.phone}</td>
                        <td data-label="${nameLabel}">${driver.name}</td>
                        <td data-label="${surnameLabel}">${driver.surname}</td>
                        <td data-label="${carNumberLabel}">${String.valueOf(driver.car.number)}</td>
                        <td data-label="${bannedLabel}">${driver.banned}</td>
                        <td data-label="${ratingLabel}">${driver.rating}</td>
                        <td data-label="${freeLabel}">${driver.free}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <taxi:pagination command="display_taxidrivers"/>

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
