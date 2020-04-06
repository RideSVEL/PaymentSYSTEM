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
    <title><fmt:message key="jsp.cards"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:set var="activeCards" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br>
<h4 align="center">Card user with login - ${userLogin}</h4>
<br>
<div id="main-container" class="container">
    <div class="table-responsive">
        <table id="cards_table" class="table table-dark table-hover table-striped">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="jsp.Name"/></th>
                <th scope="col"><fmt:message key="jsp.number"/></th>
                <th scope="col"><fmt:message key="jsp.money"/></th>
                <th scope="col"><fmt:message key="jsp.activity"/></th>
                <th scope="col" class="content center"><fmt:message key="jsp.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="k" value="0"/>
            <c:forEach var="card" items="${cards}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <th scope="row"><c:out value="${k}"/></th>
                    <td>${card.name}</td>
                    <td>${card.number}</td>
                    <td>${card.money}</td>
                    <td>
                        <c:if test="${card.activityId == 0}">
                            <fmt:message key="jsp.activity.active"/>
                        </c:if>
                        <c:if test="${card.activityId == 1}">
                            <fmt:message key="jsp.activity.blocked"/>
                        </c:if>
                    <td>
                        <c:if test="${card.activityId == 0}">
                            <form id="block_user" action="controller" method="post">
                                <input type="hidden" name="command" value="blockCard"/>
                                <input type="hidden" name="card_id" value="${card.id}"/>
                                <input class="btn btn-danger" type="submit" value="<fmt:message key="jsp.block"/>"/>
                            </form>
                        </c:if>
                        <c:if test="${card.activityId == 1}">
                            <form id="unblock_user" action="controller" method="post">
                                <input type="hidden" name="command" value="unblockCard"/>
                                <input type="hidden" name="card_id" value="${card.id}"/>
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
