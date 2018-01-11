<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="local.page.main.title" var="pageTitleLabel"/>

    <fmt:message bundle="${loc}" key="local.login.activity" var="signinPageLabel"/>
    <fmt:message bundle="${loc}" key="local.signup" var="signupPageLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="WEB-INF/jsp/include/language.jsp" />
<a href="/signin">${signinPageLabel}</a>
<a href="/signup">${signupPageLabel}</a>
</body>
</html>