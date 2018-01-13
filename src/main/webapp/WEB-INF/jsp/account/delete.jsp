<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>

    <fmt:message bundle="${loc}" key="title.delete" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="account.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="activity.sure" var="sureLabel"/>
    <fmt:message bundle="${loc}" key="activity.delete" var="deleteLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<form action="/controller?command=delete_administrator" method="post">
    <label for="password">${passwordLabel}</label>
    <input type="password" name="password" id="password" required>
    <label for="sure-box">${sureLabel}</label>
    <input type="checkbox" id="sure-box" onchange="document.getElementById('delete-button').disabled = !this.checked;" required>
    <input type="submit" id="delete-button" disabled="true" value="${deleteLabel}">
</form>
</body>
</html>
