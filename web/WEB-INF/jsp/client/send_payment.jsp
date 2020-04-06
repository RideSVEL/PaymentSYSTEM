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
    <title><fmt:message key="jsp.send.payment"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="login-page">
    <br>
    <form id="sendingPayment" action="controller" method="get">
        <input type="hidden" name="command" value="checkPayment"/>
        <label class="text-white" for="card"><fmt:message key="jsp.select_card_for_payment"/></label>
        <select id="card" name="card_id" class="form-control shadow-lg">
            <c:forEach var="card" items="${cards}">
                <c:if test="${card.activityId == 0}">
                    <option value="${card.id}">${card.number}</option>
                </c:if>
            </c:forEach>
        </select>
        <br>
        <div class="form-group">
            <a class="text-white"><fmt:message key="jsp.max_sum"/></a>
            <label class="text-white" for="sum"><fmt:message key="jsp.sum"/></label>
            <div class="input-group shadow-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">&#8372;</span>
                </div>
                <input class="form-control" id="sum" name="sum" type="number" aria-label="Sum"
                       aria-describedby="basic-addon1" placeholder="<fmt:message key="jsp.sum"/>" required pattern="[0-9]+"/>
            </div>
        </div>
        <div class="form-group">
            <label class="text-white" for="destination"><fmt:message key="jsp.card.destination"/></label>
            <input class="form-control shadow-lg" id="destination" name="destination" type="number" required pattern="[0-9]{12}"/><br/>
        </div>
        <input class="btn btn-success shadow-lg" type="submit" value="<fmt:message key="jsp.accept"/>">
    </form>
</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
