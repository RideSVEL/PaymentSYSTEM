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
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <h2><fmt:message key="jsp.are_you_confirm_your_action"/></h2>
            <br>
            <form id="block_card" action="controller" method="post">
                <input type="hidden" name="command" value="blockUserCard"/>
                <input type="hidden" name="card_id" value="${card_id}"/>
                <input type="submit" value="<fmt:message key="jsp.block"/>"/>
            </form>
            <form id="showCards" action="controller" method="post">
                <input type="hidden" name="command" value="userShowCards"/>
                <input type="submit" value="<fmt:message key="jsp.reject"/>"/>
            </form>
            <%-- CONTENT --%>


            <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
