<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="LOCALIZE"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
    <select name="locale" onchange="this.form.submit()">
        <c:choose>
            <c:when test="${sessionScope.locale == 'be'}">
                <option value="be">Беларуская</option>
                <option value="en">English</option>
            </c:when>
            <c:when test="${sessionScope.locale == 'en'}">
                <option value="en">English</option>
                <option value="be">Беларуская</option>
            </c:when>
            <c:otherwise>
                <option value="be">Беларуская</option>
                <option value="en">English</option>
            </c:otherwise>
        </c:choose>
    </select>
</form>