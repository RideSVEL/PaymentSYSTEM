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
    <title><fmt:message key="jsp.header.income"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body style="background: linear-gradient(to bottom left, #af3c52 ,#0ea4ac);">
<c:set var="activeIncome" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br>
<div id="main-container" class="container-fluid">

    <form id="sorting_payments" action="controller" method="post">
        <input type="hidden" name="command" value="showIncome"/>
        <select name="sorting">
            <option value="date"><fmt:message key="jsp.sorting.date"/></option>
            <option value="number"><fmt:message key="jsp.number"/></option>
        </select>
        <select name="order">
            <option value="ascending"><fmt:message key="jsp.sorting.ascending"/></option>
            <option value="descending"><fmt:message key="jsp.sorting.descending"/></option>
        </select>
        <select name="filter">
            <option value="all"><fmt:message key="jsp.sorting.all"/></option>
            <option value="send"><fmt:message key="jsp.sorting.send"/></option>
            <option value="prepared"><fmt:message key="jsp.sorting.prepared"/></option>
        </select>
        <input type="submit" value="<fmt:message key="jsp.sort"/>">
    </form>
    <br>
    <div class="table-responsive">
        <table class="table table-hover table-striped table-dark">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="jsp.card.number"/></th>
                <th scope="col"><fmt:message key="jsp.sender"/></th>
                <th scope="col"><fmt:message key="jsp.number.payments"/></th>
                <th scope="col"><fmt:message key="jsp.date"/></th>
                <th scope="col"><fmt:message key="jsp.money"/></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="k" value="0"/>
            <c:forEach var="payment" items="${payments}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <th scope="row"><c:out value="${k}"/></th>
                    <td>${payment.cardDestinationNumber}</td>
                    <td><c:if test="${payment.cardId != null && payment.cardId != 0}">
                        ${payment.cardNumber}</c:if>
                        <c:if test="${payment.cardId == null || payment.cardId == 0}"><fmt:message
                                key="jsp.refill"/></c:if>
                    </td>
                    <td>${payment.id}</td>
                    <td>${payment.date}</td>
                    <td>${payment.money}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
