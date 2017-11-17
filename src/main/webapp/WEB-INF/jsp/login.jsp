<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.user.login" var="loginLabel"/>
    <fmt:message bundle="${loc}" key="local.login.activity" var="loginPageLabel"/>
    <fmt:message bundle="${loc}" key="local.user.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="local.login.button" var="loginButtonLabel"/>
    <fmt:message bundle="${loc}" key="local.application.name" var="applicationNameLabel"/>

    <title>${loginPageLabel} - ${applicationNameLabel}</title>
</head>
<body>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="LOCALIZE"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
    <select name="locale">
        <option value="be">Беларуская</option>
        <option value="en">English</option>
    </select>
    <input type="submit" value="OK">
</form>
<form action="AccountController" method="get">
    <input type="hidden" name="command" value="authentication">

    ${loginLabel}: <input type="text" name="login" value="">
    ${passwordLabel}: <input type="password" name="password" value="">
    <input type="submit" value="${loginButtonLabel}">
</form>
</body>
</html>
