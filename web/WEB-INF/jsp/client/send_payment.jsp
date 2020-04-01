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
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <br>
            <form id="sendingPayment" action="controller" method="get">
                <input type="hidden" name="command" value="checkPayment"/>
                <a><fmt:message key="jsp.select_card_for_payment"/></a>
                <select name="card_id">
                    <c:forEach var="card" items="${cards}">
                        <c:if test="${card.activityId == 0}">
                            <option value="${card.id}">${card.number}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <br><br>
                <a><fmt:message key="jsp.max_sum"/></a>
                <fieldset>
                    <legend><fmt:message key="jsp.sum"/></legend>
                    <input name="sum" type="number"/><br/>
                </fieldset>
                <br>
                <fieldset>
                    <legend><fmt:message key="jsp.card.destination"/></legend>
                    <input name="destination" type="number"/><br/>
                </fieldset>
                <input type="submit" value="<fmt:message key="jsp.accept"/>">
            </form>
            <br>


            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
