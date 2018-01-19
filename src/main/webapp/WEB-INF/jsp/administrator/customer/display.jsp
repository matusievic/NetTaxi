<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="taxi" uri="/WEB-INF/tag/taxi" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="account.id" var="idLabel"/>
    <fmt:message bundle="${loc}" key="account.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="account.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="account.surname" var="surnameLabel"/>
    <fmt:message bundle="${loc}" key="account.banned" var="bannedLabel"/>
    <fmt:message bundle="${loc}" key="account.discount" var="discountLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="blockLabel"/>
    <fmt:message bundle="${loc}" key="activity.unblock" var="unblockLabel"/>
    <fmt:message bundle="${loc}" key="activity.back" var="backLabel"/>
    <fmt:message bundle="${loc}" key="title.customer" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="application.name" var="applicationNameLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<table>
    <tr>
        <th>${idLabel}</th>
        <td>${customer.id}</td>
    </tr>
    <tr>
        <th>${nameLabel}</th>
        <td>${customer.name}</td>
    </tr>
    <tr>
        <th>${surnameLabel}</th>
        <td>${customer.surname}</td>
    </tr>
    <tr>
        <th>${phoneLabel}</th>
        <td>${customer.phone}</td>
    </tr>
    <tr>
        <th>${bannedLabel}</th>
        <td>${customer.banned}</td>
    </tr>
    <tr>
        <th>${discountLabel}</th>
        <td>${customer.discount}</td>
    </tr>
</table>
<taxi:admin>
    <c:choose>
        <c:when test="${!customer.banned}">
            <a href="/controller?command=block_customer&id=${customer.id}">${blockLabel}</a>
        </c:when>
        <c:when test="${customer.banned}">
            <a href="/controller?command=unblock_customer&id=${customer.id}">${unblockLabel}</a>
        </c:when>
    </c:choose>
<form action="/controller?command=discount_customer&id=${customer.id}" method="post">
    <label for="discount">Discount: </label>
    <input type="text" name="discount" id="discount" placeholder="${discountLabel}">
    <input type="submit" value="OK">
</form>
<a href="/controller?command=display_customers">${backLabel}</a>
</taxi:admin>
</body>
</html>
