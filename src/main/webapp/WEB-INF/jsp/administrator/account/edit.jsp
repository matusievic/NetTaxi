<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="common.account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="common.account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="common.account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="common.account.old_password" var="oldPasswordLabel"/>
    <fmt:message bundle="${loc}" key="common.account.password" var="firstPasswordLabel"/>
    <fmt:message bundle="${loc}" key="common.account.repeat_password" var="secondPasswordLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.account.title" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<form action="/controller?command=update_administrator" method="post">
    <label for="name">${nameLabel}</label>
    <input type="text" name="name" id="name" value="${user.name}" required>

    <label for="surname">${surnameLabel}</label>
    <input type="text" name="surname" id="surname" value="${user.surname}" required>

    <label for="phone">${phoneLabel}</label>
    <input type="text" name="phone" id="phone" value="${user.phone}" required>

    <label for="old-password">${oldPasswordLabel}</label>
    <input type="password" name="oldPassword" id="old-password">

    <label for="first-password">${firstPasswordLabel}</label>
    <input type="password" name="firstPassword" id="first-password">

    <label for="second-password">${secondPasswordLabel}</label>
    <input type="password" name="secondPassword" id="second-password">
    <input type="submit" value="OK">
</form>
</body>
</html>
