<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>
    <fmt:message bundle="${loc}" key="title.order" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_in" var="signinPageLabel"/>
    <fmt:message bundle="${loc}" key="account.sign_up" var="signupPageLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="../common/language.jsp" />
<body>
<form action="/controller?command=order">
</form>
</body>
</html>
