<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="user.customer.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="user.customer.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="user.customer.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="user.customer.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="user.customer.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="user.customer.discount" var="discountLabel"/>
    <fmt:message bundle="${loc}" key="page.administrator.customers.title" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>${idLabel}</th>
        <th>${phoneLabel}</th>
        <th>${nameLabel}</th>
        <th>${surnameLabel}</th>
        <th>${bannedLabel}</th>
        <th>${discountLabel}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td><a href="/controller?command=display_customer&id=${customer.id}">${customer.id}</a></td>
            <td>${customer.phone}</td>
            <td>${customer.name}</td>
            <td>${customer.surname}</td>
            <td>${customer.banned}</td>
            <td>${customer.discount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
