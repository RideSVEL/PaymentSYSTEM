<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.login"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <style>
        .login-page {
            text-align: center;
            position: relative;
            width: 250px;
            padding: 10% 0 0;
            margin: 0 auto;
        }
    </style>
</head>
<body style="background: linear-gradient(to bottom left, #af3c52 ,#0ea4ac);">

<%-- HEADER --%>
<c:set var="activeLogin" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- HEADER --%>

<c:if test="${not empty user}">
    <b>You are already login</b>
</c:if>
<c:if test="${empty user and title ne 'Login'}">


    <div id="main-container" class="login-page">

        <form role="form" id="login_form" action="controller" method="post" target="_parent">
            <input type="hidden" name="command" value="login"/>
            <div class="form-group ">
                <label for="login" class="text-white"><fmt:message key="jsp.login"/></label>
                <div class="input-group shadow-lg">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">@</span>
                    </div>
                    <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                           aria-describedby="basic-addon1" name="login" id="login">
                </div>
            </div>
            <div class="form-group ">
                <label for="password" class="text-white"><fmt:message key="jsp.password"/></label>
                <input type="password" name="password" id="password" class="form-control shadow-lg"
                       placeholder="Password"/>
            </div>
            <input type="submit" class="btn btn-success btn-block shadow-lg "
                   value="<fmt:message key="jsp.header.login"/>">
        </form>
        <p class="mt-2" style="font-size: small">
            Don't have an account yet? <a href="${pageContext.request.contextPath}/register">Register now</a>
        </p>
    </div>

</c:if>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>