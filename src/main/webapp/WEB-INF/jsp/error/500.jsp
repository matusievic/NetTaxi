<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.page.500.title" var="pageTitleLabel"/>
    <fmt:message bundle="${loc}" key="local.application.name" var="applicationNameLabel"/>

    <fmt:message bundle="${loc}" key="local.page.500.message.title" var="messageTitleLabel"/>
    <fmt:message bundle="${loc}" key="local.page.500.message.description" var="messageDescriptionLabel"/>

    <title>${pageTitleLabel} - ${applicationNameLabel}</title>
</head>
<body>
<jsp:directive.include file="include/language.jsp"/>

${messageTitleLabel} <br>
${messageDescriptionLabel}

</body>
</html>
