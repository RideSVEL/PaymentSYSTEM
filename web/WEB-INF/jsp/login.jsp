<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.login"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<%-- HEADER --%>
<c:set var="activeLogin" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- HEADER --%>

<c:if test="${not empty user}">
    <b>You are already login</b>
</c:if>
<c:if test="${empty user and title ne 'Login'}">
    <div class="form-login">
        <div id="main-container" class="login-page">

            <form role="form" id="login_form" action="controller" method="post" target="_parent">
                <input type="hidden" name="command" value="login"/>
                <div class="form-group ">
                    <label for="login"><fmt:message key="jsp.username"/></label>
                    <div class="input-group shadow-lg">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">@</span>
                        </div>
                        <input type="text" class="form-control" placeholder="<fmt:message key="jsp.username"/>" aria-label="Username"
                               aria-describedby="basic-addon1" name="login" id="login" required pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}">
                    </div>
                </div>
                <div class="form-group ">
                    <label for="password"><fmt:message key="jsp.password"/></label>
                    <input type="password" name="password" id="password" class="form-control shadow-lg"
                           placeholder="<fmt:message key="jsp.password"/>" required pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}"/>
                </div>
                <input type="submit" class="btn btn-success btn-block shadow-lg "
                       value="<fmt:message key="jsp.header.login"/>" >
            </form>
            <p class="mt-2" style="font-size: small">
                <fmt:message key="jsp.dont_have_an_account"/> <a href="${pageContext.request.contextPath}/register"><fmt:message key="jsp.register_now"/></a>
            </p>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>