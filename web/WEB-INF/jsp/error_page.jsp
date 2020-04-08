<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.error"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main-container" class="container-fluid">
    <br>
    <h2 class="error">
        <fmt:message key="jsp.the_following_error"/>:
    </h2>

    <%-- this way we obtain an information about an exception (if it has been occurred) --%>
    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
    <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <c:if test="${not empty code}">
        <h3><fmt:message key="jsp.error_code"/> ${code}</h3>
    </c:if>

    <c:if test="${not empty message}">
        <h3>"${message}</h3>
    </c:if>

    <c:if test="${not empty exception}">
        <% exception.printStackTrace(new PrintWriter(out)); %>
    </c:if>

    <%-- if we get this page using forward --%>
    <c:if test="${not empty requestScope.errorMessage}">
        <h3><fmt:message key="${requestScope.errorMessage}"/></h3>
        <br>
		Date: <ct:customTag/>
        <br>
        <ex:AllFine>
            Everything will be fine.
        </ex:AllFine>
    </c:if>

</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>