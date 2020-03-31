<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.header.payments"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body class="background-gradient">
<c:set var="activePayments" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br>
<div id="main-container" class="container-fluid p5percent">
    <form id="sorting_payments" action="controller" method="post">
        <input type="hidden" name="command" value="showPayments"/>
        <select name="sorting">
            <option value="date"><fmt:message key="jsp.sorting.date"/></option>
            <option value="number"><fmt:message key="jsp.sorting.number"/></option>
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
        <table id="payment_table" class="table table-hover table-dark table-striped" style="padding: 10px">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="jsp.card.number"/></th>
                <th scope="col"><fmt:message key="jsp.recipient"/></th>
                <th scope="col"><fmt:message key="jsp.number.payments"/></th>
                <th scope="col"><fmt:message key="jsp.date"/></th>
                <th scope="col"><fmt:message key="jsp.money"/></th>
                <th scope="col"><fmt:message key="jsp.balance.after.payment"/></th>
                <th scope="col"><fmt:message key="jsp.status"/></th>
                <th scope="col" class="content center"><fmt:message key="jsp.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="k" value="0"/>
            <c:forEach var="payment" items="${payments}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <th scope="row"><c:out value="${k}"/></th>
                    <td>${payment.cardNumber}</td>
                    <td>${payment.cardDestinationNumber}</td>
                    <td>${payment.id}</td>
                    <td>${payment.date}</td>
                    <td>${payment.money}</td>
                    <td>${payment.balance}</td>
                    <td>
                        <c:if test="${payment.statusId == 0}">
                            <fmt:message key="jsp.sorting.prepared"/>
                        </c:if>
                        <c:if test="${payment.statusId == 1}">
                            <fmt:message key="jsp.sorting.send"/>
                        </c:if>

                    <td>
                        <c:if test="${payment.statusId == 0}">
                            <form id="block_card" action="controller" method="post">
                                <input type="hidden" name="command" value="confirmDefer"/>
                                <input type="hidden" name="payment_id" value="${payment.id}"/>
                                <input class="btn btn-primary" type="submit"
                                       value="<fmt:message key="jsp.send.payment"/>"/>
                            </form>
                            <form id="deleteDefer" action="controller" method="post">
                                <input type="hidden" name="command" value="deleteDefer"/>
                                <input type="hidden" name="payment_id" value="${payment.id}"/>
                                <input class="btn btn-danger" type="submit" value="<fmt:message key="jsp.delete"/>"/>
                            </form>
                        </c:if>
                        <c:if test="${payment.statusId == 1}">
                            <form id="block_card" action="controller" method="post">
                                <input type="hidden" name="command" value="getPDF"/>
                                <input type="hidden" name="payment_id" value="${payment.id}"/>
                                <input class="btn btn-info" type="submit" value="<fmt:message key="jsp.get.pdf"/>"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
