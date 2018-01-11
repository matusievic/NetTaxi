<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.user.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="local.login.activity" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="local.user.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="local.login.button" var="signinButtonLabel"/>
    <fmt:message bundle="${loc}" key="local.application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="include/language.jsp" />
<c:out value="${error}"/>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="authenticate">

    <input type="text" name="phone" placeholder="${phoneLabel}" required>
    <input type="password" name="password" placeholder="${passwordLabel}" required>
    <input type="submit" value="${signinButtonLabel}">
</form>
</body>
</html>
