<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:if test="${requestScope.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
    <fmt:setBundle basename="resources"/>
    <c:set var="currentLocale" value="${param.locale}" scope="session"/>
</c:if>

<html>
<head>
    <title><fmt:message key='jsp.header.Settings'/> &bull; PAYMENT SYSTEM</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>


<body class="background-gradient">
<c:set var="activeSettings" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="login-page">
    <form id="settings_form" action="controller" method="post">
        <input type="hidden" name="command" value="updateSettings"/>
        <label for="language" class="text-white"><fmt:message key='jsp.header.language'/></label>
        <select name="locale" id="language" class="form-control shadow-lg">
            <c:forEach items="${applicationScope.locales}" var="locale">
                <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                <option value="${locale.key}" ${selected}><fmt:message key="${locale.value}"/></option>
            </c:forEach>
        </select>
        <br>
        <c:if test="${not empty user and title ne 'Login'}">
            <div class="form-group">
                <label for="firstName" class="text-white"><fmt:message key='jsp.name'/></label>
                <input id="firstName" class="form-control shadow-lg" name="name" value="${user.firstName}" required
                       pattern="[a-zA-Z&#1072;-&#1103;&#1040;-&#1071;]{2,20}">
            </div>
            <div class="form-group">
                <label for="lastName" class="text-white"><fmt:message key='jsp.lastName'/></label>
                <input id="lastName" class="form-control shadow-lg" name="lastName" value="${user.lastName}" required
                       pattern="[a-zA-Z&#1072;-&#1103;&#1040;-&#1071;]{2,20}">
            </div>
        </c:if>
        <input class="btn btn-success btn-block shadow-lg" type="submit" value="<fmt:message key='jsp.save'/>"><br/>
    </form>


</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>