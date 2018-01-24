<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${sessionScope.locale == 'be'}">
        <a href="/controller?command=LOCALIZE&from=${pageContext.request.requestURI}&locale=en">English</a>
    </c:when>
    <c:when test="${sessionScope.locale == 'en'}">
        <a href="/controller?command=LOCALIZE&from=${pageContext.request.requestURI}&locale=be">Беларуская</a>
    </c:when>
    <c:otherwise>
        <a href="/controller?command=LOCALIZE&from=${pageContext.request.requestURI}&locale=be">Беларуская</a>
    </c:otherwise>
</c:choose>
</form>