<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.sign_up" var="signupPageLabel"/>

    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="account.repeat_password" var="repeatPasswordLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signupButtonLabel"/>

    <title>${signupPageLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="../include/language.jsp" />
<form action="/controller" method="post">
    <input type="hidden" name="command" value="register_customer">
    <input type="text" name="phone" placeholder="${phoneLabel}" required><br>
    <input type="text" name="name" placeholder="${nameLabel}" required><br>
    <input type="text" name="surname" placeholder="${surnameLabel}" required><br>
    <input type="password" name="first_password" placeholder="${passwordLabel}" required><br>
    <input type="password" name="second_password" placeholder="${repeatPasswordLabel}" required><br>
    <input type="submit" value="${signupPageLabel}">
</form>
</body>
</html>
