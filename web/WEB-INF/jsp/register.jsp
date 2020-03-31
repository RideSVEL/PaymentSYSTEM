<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.register"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body class="background-gradient">
<%-- HEADER --%>
<c:set var="activeRegister" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- HEADER --%>
<c:if test="${not empty user}">
    <b>You are already register</b>
</c:if>
<c:if test="${empty user and title ne 'Login'}">
    <div id="main-container" class="login-page">

        <form id="register_form" action="controller" method="post" role="form">
            <input type="hidden" name="command" value="register"/>
                <div class="form-group ">
                    <label for="login" class="text-white"><fmt:message key="jsp.login"/></label>
                    <div class="input-group shadow-lg">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">@</span>
                        </div>
                        <input type="text" class="form-control " placeholder="Username" aria-label="Username"
                               aria-describedby="basic-addon1" name="login" id="login">
                    </div>
                </div>
                <div class="form-group ">
                    <label for="password" class="text-white"><fmt:message key="jsp.password"/></label>
                    <input type="password" name="password" id="password" class="form-control shadow-lg"
                           placeholder="Password"/>
                </div>
                <div class="form-group ">
                    <label for="firstName" class="text-white"><fmt:message key="jsp.Name"/></label>
                    <input name="first_name" id="firstName" class="form-control shadow-lg"
                           placeholder="First name"/>
                </div>
                <div class="form-group ">
                    <label for="lastName" class="text-white"><fmt:message key="jsp.lastName"/></label>
                    <input name="last_name" id="lastName" class="form-control shadow-lg"
                           placeholder="Last name"/>
                </div>
                <input type="submit" class="btn btn-success btn-block shadow-lg" value="<fmt:message key="jsp.register"/>">
        </form>
        <p class="mt-2" style="font-size: small">
            Already register? <a href="${pageContext.request.contextPath}/login">Login now</a>
        </p>
    </div>
</c:if>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>