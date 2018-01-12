<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="user.administrator.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="user.administrator.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="user.administrator.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="user.administrator.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="user.administrator.password" var="passwordLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.account.edit" var="editLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.account.delete" var="deleteLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.account.title" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<a href="/controller?command=display_taxidrivers">Drivers</a>
<a href="/controller?command=display_customers">Customers</a>
<table>
    <tr>
        <th>${idLabel}</th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th>${nameLabel}</th>
        <td>${user.name}</td>
    </tr>
    <tr>
        <th>${surnameLabel}</th>
        <td>${user.surname}</td>
    </tr>
    <tr>
        <th>${phoneLabel}</th>
        <td>${user.phone}</td>
    </tr>
    <tr>
        <th>${passwordLabel}</th>
        <th>******</th>
    </tr>
</table>
<a href="/administrator/account/edit">${editLabel}</a>
<a href="/administrator/account/delete">${deleteLabel}</a>
</body>
</html>
