<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.register"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%-- HEADER --%>
<c:set var="activeRegister" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- HEADER --%>
<c:if test="${not empty user}">
    <b>You are already register</b>
</c:if>
<c:if test="${empty user and title ne 'Login'}">
    <div class="form-register">
        <div id="main-container" class="register-page">

            <form id="register_form" action="controller" method="post" role="form">
                <input type="hidden" name="command" value="register"/>
                <div class="form-group ">
                    <label for="login"><fmt:message key="jsp.username"/></label>
                    <div class="input-group shadow-lg">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">&#64;</span>
                        </div>
                        <input type="text" class="form-control " placeholder="<fmt:message key="jsp.username"/>" aria-label="Username"
                               aria-describedby="basic-addon1" name="login" id="login"  required pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}">
                    </div>
                </div>
                <div class="form-group ">
                    <label for="password" ><fmt:message key="jsp.password"/></label>
                    <input type="password" name="password" id="password" class="form-control shadow-lg"
                           placeholder="<fmt:message key="jsp.password"/>"  required pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}"/>
                </div>
                <div class="form-group ">
                    <label for="firstName"><fmt:message key="jsp.Name"/></label>
                    <input name="first_name" id="firstName" class="form-control shadow-lg"
                           placeholder="<fmt:message key="jsp.Name"/>" required pattern="[a-zA-Z&#1072;-&#1103;&#1040;-&#1071;]+"/>
                </div>
                <div class="form-group ">
                    <label for="lastName"><fmt:message key="jsp.lastName"/></label>
                    <input name="last_name" id="lastName" class="form-control shadow-lg"
                           placeholder="<fmt:message key="jsp.lastName"/>" required pattern="[a-zA-Z&#1072;-&#1103;&#1040;-&#1071;]+"/>
                </div>
                <input type="submit" class="btn btn-success btn-block shadow-lg"
                       value="<fmt:message key="jsp.register"/>">
            </form>
            <p class="mt-2" style="font-size: small">
                <fmt:message key="jsp.already_register"/> <a href="${pageContext.request.contextPath}/login"><fmt:message key="jsp.login_now"/></a>
            </p>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>