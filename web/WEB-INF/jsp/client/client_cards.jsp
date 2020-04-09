<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <title><fmt:message key="jsp.cards"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:set var="activeCards" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<br>


<div class="container-fluid p5percent">
    <c:if test="${sorting == 'name'}">
        <c:set var="selectedName" value="selected" scope="page"/>
    </c:if>
    <c:if test="${sorting == 'number'}">
        <c:set var="selectedNumber" value="selected" scope="page"/>
    </c:if>
    <c:if test="${sorting == 'money'}">
        <c:set var="selectedMoney" value="selected" scope="page"/>
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
    <c:if test="${filter == 'active'}">
        <c:set var="selectedActive" value="selected" scope="page"/>
    </c:if>
    <c:if test="${filter == 'block'}">
        <c:set var="selectedBlock" value="selected" scope="page"/>
    </c:if>
    <form id="sorting_cards" action="controller" method="post">
        <input type="hidden" name="command" value="userShowCards"/>
        <label>
            <select name="sorting" class="form-control bg-transparent shadow-lg text-white">
                <option class="bg-dark" ${selectedName} value="name"><fmt:message key="jsp.sorting.name"/></option>
                <option class="bg-dark" ${selectedNumber} value="number"><fmt:message
                        key="jsp.sorting.number"/></option>
                <option class="bg-dark" ${selectedMoney} value="money"><fmt:message key="jsp.sorting.money"/></option>
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
                <option class="bg-dark" ${selectedActive} value="active"><fmt:message
                        key="jsp.sorting.active"/></option>
                <option class="bg-dark" ${selectedBlock} value="block"><fmt:message key="jsp.sorting.block"/></option>
            </select>
        </label>
        <input class="btn btn-info shadow-lg" type="submit" value="<fmt:message key="jsp.sort"/>">
    </form>
</div>

<div class="container p5percent">
    <div class="row">
        <c:forEach var="card" items="${cards}">
            <c:set var="bg" value="bg-dark"/>
            <c:if test="${card.activityId == 1}">
                <c:set var="bg" value="bg-danger"/>
                <c:if test="${card.requestId == 1}">
                    <c:set var="bg" value="bg-info"/>
                </c:if>
            </c:if>
            <div class="card text-white ${bg}  mb-3" style="width: 18rem;">
                <h5 class="card-header text-uppercase">
                    <div class="spinner-grow spinner-grow-sm text-light" role="status">
                        <span class="sr-only">Loading...</span></div>
                        ${card.name}</h5>
                <div class="card-body">
                    <h6 class="card-title"><fmt:message key="jsp.card.number"/>: ${card.number}</h6>
                    <p class="card-text"><fmt:message key="jsp.balance"/>: ${card.money} UAN</p>
                    <p>
                        <c:if test="${card.activityId == 0}">
                            <fmt:message key="jsp.activity.active"/>
                        </c:if>
                        <c:if test="${card.activityId == 1}">
                            <fmt:message key="jsp.activity.blocked"/>
                        </c:if>
                    </p>
                </div>
                <div class="card-footer">
                    <c:if test="${card.requestId == 0}">
                        <c:if test="${card.activityId == 0}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="actionBlock"/>
                                <input type="hidden" name="card_id" value="${card.id}"/>
                                <input class="btn btn-danger" type="submit"
                                       value="<fmt:message key="jsp.block"/>"/>
                            </form>
                        </c:if>
                        <c:if test="${card.activityId == 1}">
                            <div>
                                <form action="controller" method="post">
                            <input type="hidden" name="command" value="requestUserCardUnblock"/>
                            <input type="hidden" name="card_id" value="${card.id}"/>
                            <input class="btn btn-success" type="submit"
                                   value="<fmt:message key="jsp.unblock.request"/>"/>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${card.requestId == 1}">
                        <fmt:message key="jsp.consideration"/>
                    </c:if>
                </div>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </c:forEach>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
