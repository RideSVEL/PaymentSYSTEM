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
    <title><fmt:message key="jsp.header.users"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <script type="text/javascript" src="script/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
</head>
<body>
<c:set var="activeUsers" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br><br>
<div id="main-container" class="container">
    <form class="form-inline" action="controller" method="post">
    <input type="hidden" name="command" value="createSelectionPayment"/>
    <input class="form-control mr-sm-2" type="date"
           placeholder="Enter your date"
           aria-label="selection" name="selection" id="selection">
    <input class="btn btn-success" type="submit"
           value="SELECTION">
    </form>
    <br><br>
    <div class="table-responsive">
        <table id="users_table" class="table table-dark table-hover table-striped ">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="jsp.login"/></th>
                <th scope="col"><fmt:message key="jsp.name"/></th>
                <th scope="col"><fmt:message key="jsp.lastName"/></th>
                <th scope="col"><fmt:message key="jsp.activity"/></th>
                <th scope="col"><fmt:message key="jsp.check"/></th>
                <th scope="col"><fmt:message key="jsp.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="k" value="0"/>
            <c:forEach var="user" items="${users}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <th scope="row"><c:out value="${k}"/></th>
                    <td>${user.login}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <c:if test="${user.activityId == 0}">
                            <fmt:message key="jsp.activity.active"/>
                        </c:if>
                        <c:if test="${user.activityId == 1}">
                            <fmt:message key="jsp.activity.blocked"/>
                        </c:if>
                    <td>
                        <form id="listUsers" action="controller" method="post">
                            <input type="hidden" name="command" value="showCards"/>
                            <input type="hidden" name="user_id" value="${user.id}"/>
                            <input class="btn btn-primary" type="submit" value="<fmt:message key="jsp.cards"/>"/>
                        </form>
                    </td>
                    <td>
                        <c:if test="${user.activityId == 0}">
                            <form id="block_user" action="controller" method="post">
                                <input type="hidden" name="command" value="blockUser"/>
                                <input type="hidden" name="user_id" value="${user.id}"/>
                                <input class="btn btn-danger" type="submit" value="<fmt:message key="jsp.block"/>"/>
                            </form>
                        </c:if>
                        <c:if test="${user.activityId == 1}">
                            <form id="unblock_user" action="controller" method="post">
                                <input type="hidden" name="command" value="unblockUser"/>
                                <input type="hidden" name="user_id" value="${user.id}"/>
                                <input class="btn btn-success" type="submit" value="<fmt:message key="jsp.unblock"/>"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center">
            <c:if test="${requestScope.searching.equals(true)}">
                <form id="go_back" action="controller" method="post">
                    <input type="hidden" name="command" value="listUsers"/>
                    <input class="btn btn-primary" type="submit" value="Go back"/>
                </form>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
