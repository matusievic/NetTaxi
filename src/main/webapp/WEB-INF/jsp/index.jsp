<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="include/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="/css/index.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.main" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_in" var="signInLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signUpLabel"/>

    <fmt:message bundle="${loc}" key="content.index.features" var="featuresLabel"/>
    <fmt:message bundle="${loc}" key="content.index.feature.cheap" var="cheapLabel"/>
    <fmt:message bundle="${loc}" key="content.index.feature.fast" var="fastLabel"/>
    <fmt:message bundle="${loc}" key="content.index.feature.online_booking" var="onlineBookingLabel"/>
    <fmt:message bundle="${loc}" key="content.index.feature.safe" var="safeLabel"/>
    <fmt:message bundle="${loc}" key="content.index.who_we_are" var="whoWeAreLabel"/>
    <fmt:message bundle="${loc}" key="content.index.what_people_say" var="whatPeopleSayLabel"/>
    <fmt:message bundle="${loc}" key="content.index.or" var="orLabel"/>
    <fmt:message bundle="${loc}" key="content.index.book" var="bookLabel"/>
    <fmt:message bundle="${loc}" key="content.index.john_doe" var="johnDoeLabel"/>
    <fmt:message bundle="${loc}" key="content.index.jan_janouski" var="janJanouskiLabel"/>
    <fmt:message bundle="${loc}" key="content.index.anonym" var="anonymLabel"/>
    <fmt:message bundle="${loc}" key="content.index.how_to_order" var="howToOrderLabel"/>
    <fmt:message bundle="${loc}" key="content.index.city" var="cityLabel"/>
    <fmt:message bundle="${loc}" key="content.index.street" var="streetLabel"/>
    <fmt:message bundle="${loc}" key="content.index.contact" var="contactLabel"/>
    <fmt:message bundle="${loc}" key="content.index.wait" var="waitLabel"/>
    <fmt:message bundle="${loc}" key="content.index.go" var="goLabel"/>
    <fmt:message bundle="${loc}" key="content.index.about" var="aboutLabel"/>

    <script type="text/javascript" src="https://api-maps.yandex.ru/2.1/?lang=en_US"></script>
    <script type="text/javascript" src="/js/contact.js"></script>
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
        <h3>${whoWeAreLabel}</h3>
        <img src="img/taxi-car.png">
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce finibus sapien sit amet enim sollicitudin,
            sit amet mollis urna pulvinar. Donec a mattis dolor, nec rutrum mi. Sed vestibulum lorem et justo viverra
            aliquet. Maecenas faucibus hendrerit feugiat. Ut vitae lacus ligula. Pellentesque tincidunt pharetra porta.
            Ut arcu libero, malesuada ac diam sed, scelerisque faucibus est. Quisque facilisis aliquet gravida. Nunc nec
            elit eget est sagittis pulvinar non in mi. Nulla congue cursus sodales.
        </p>
    </section>
    <section>
        <h3>${featuresLabel}</h3>
        <p>
            <div class="features">
                <div class="feature">
                    <img src="img/fast.png">
        <p>${fastLabel}</p>
        </div>
        <div class="feature">
            <img src="img/safe.png">
            <p>${safeLabel}</p>
        </div>
        <div class="feature">
            <img src="img/cheap.png">
            <p>${cheapLabel}</p>
        </div>
        <div class="feature">
            <img src="img/online.png">
            <p>${onlineBookingLabel}</p>
        </div>
        </div>
        </p>
    </section>
    <section>
        <h3>${howToOrderLabel}</h3>
        <p>
        <ul class="timeline">
            <li>
                <span>1</span>
                <div class="timeline-item">
                    <a href="/signin">${signInLabel}</a> ${orLabel} <a href="/signup">${signUpLabel}</a>
                </div>
            </li>
            <li>
                <span>2</span>
                <div class="timeline-item">
                    ${bookLabel}
                </div>
            </li>
            <li>
                <span>3</span>
                <div class="timeline-item">
                    ${waitLabel}
                </div>
            </li>
            <li>
                <span>4</span>
                <div class="timeline-item">
                    ${goLabel}
                </div>
            </li>
        </ul>
        </p>
    </section>
    <section>
        <h3>${whatPeopleSayLabel}</h3>
        <p>
            <figure class="quote">
                <blockquote>
                    Nam porttitor, neque eget scelerisque suscipit, elit ex suscipit lectus, nec scelerisque dui nibh ac
                    magna. Proin malesuada ultricies ex, sed sodales tellus accumsan sed.
                </blockquote>
                <figcaption>${johnDoeLabel}</figcaption>
            </figure>
            <figure class="quote">
                <blockquote>
                    Morbi mi neque, hendrerit posuere risus et, vestibulum feugiat diam. Vestibulum ante ipsum primis in
                    faucibus orci luctus et ultrices posuere cubilia Curae; Nunc tortor mauris, consectetur sed dui
                    eget, varius lacinia augue.
                </blockquote>
                <figcaption>${janJanouskiLabel}</figcaption>
            </figure>
            <figure class="quote">
                <blockquote>
                    Nam viverra erat id neque congue rhoncus. Cras consequat ut nisi in consequat. Praesent a felis eu
                    ex facilisis pulvinar. Suspendisse accumsan metus eu nulla hendrerit varius. Nulla facilisi. In
                    rhoncus semper ipsum sed ullamcorper. In eu condimentum metus.
                </blockquote>
                <figcaption>${anonymLabel}</figcaption>
            </figure>
        </p>
    </section>
    <section>
        <h3>${contactLabel}</h3>
        <p>
        <div id="contact-map"></div>
        <div id="contact-info">
            <address>
                ${cityLabel}<br>
                ${streetLabel}<br>
                +375 33 333 33 33<br>
            </address>
        </div>
        </p>
    </section>
</main>
<footer>
    <div class="company">
        NetTaxi 2018
        <%@include file="include/language.jsp"%>
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