<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.header.payments"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:set var="activePayments" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br>
<c:if test="${sorting == 'date'}">
    <c:set var="selectedDate" value="selected" scope="page"/>
</c:if>
<c:if test="${sorting == 'number'}">
    <c:set var="selectedNumber" value="selected" scope="page"/>
</c:if>
<c:if test="${order == 'ascending'}">
    <c:set var="selectedAscending" value="selected" scope="page"/>
</c:if>
<c:if test="${order == 'descending'}">
    <c:set var="selectedDescending" value="selected" scope="page"/>
</c:if>
<c:if test="${filter == 'all'}">
    <c:set var="selectedAll" value="selected" scope="page"/>
</c:if>
<c:if test="${filter == 'send'}">
    <c:set var="selectedSend" value="selected" scope="page"/>
</c:if>
<c:if test="${filter == 'prepared'}">
    <c:set var="selectedPrepared" value="selected" scope="page"/>
</c:if>
<div id="main-container" class="container-fluid p5percent">
    <form id="sorting_payments" action="controller" method="post">
        <input type="hidden" name="command" value="showPayments"/>
        <label>
            <select name="sorting" class="form-control bg-transparent shadow-lg text-white">
                <option class="bg-dark" ${selectedDate} value="date"><fmt:message key="jsp.sorting.date"/></option>
                <option class="bg-dark" ${selectedNumber} value="number"><fmt:message
                        key="jsp.sorting.number"/></option>
            </select>
        </label>
        <label>
            <select name="order" class="form-control bg-transparent shadow-lg text-white">
                <option class="bg-dark" ${selectedAscending} value="ascending"><fmt:message
                        key="jsp.sorting.ascending"/></option>
                <option class="bg-dark" ${selectedDescending} value="descending"><fmt:message
                        key="jsp.sorting.descending"/></option>
            </select>
        </label>
        <label>
            <select name="filter" class="form-control bg-transparent shadow-lg text-white">
                <option class="bg-dark" ${selectedAll} value="all"><fmt:message key="jsp.sorting.all"/></option>
                <option class="bg-dark" ${selectedSend} value="send"><fmt:message key="jsp.sorting.send"/></option>
                <option class="bg-dark" ${selectedPrepared} value="prepared"><fmt:message
                        key="jsp.sorting.prepared"/></option>
            </select>
        </label>
        <input class="btn btn-info shadow-lg" type="submit" value="<fmt:message key="jsp.sort"/>">
    </form>
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
                    <td>${payment.money} UAN</td>
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
                                <input type="hidden" name="command" value="getPdf"/>
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
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
