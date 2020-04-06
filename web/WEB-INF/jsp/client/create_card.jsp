<%--
  Created by IntelliJ IDEA.
  User: sbkik
  Date: 17.03.2020
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.creating.card"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main-container" class="container-fluid login-page">
    <form class="form-control shadow-lg" id="createCard" action="controller" method="post">
        <input type="hidden" name="command" value="createCard"/>
        <label for="name"><fmt:message key="jsp.name.newCard"/></label>
        <div class="form-group">
            <br>
            <input class="form-control shadow-lg" id="name" name="name" type="text" placeholder="<fmt:message key="jsp.Name"/>" required
                   pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{2,15}"/>
        </div>
        <input class="btn btn-success shadow-lg" type="submit" value="<fmt:message key="jsp.accept"/>">
    </form>
</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
