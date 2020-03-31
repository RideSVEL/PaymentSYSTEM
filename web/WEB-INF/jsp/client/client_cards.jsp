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
<body class="background-gradient">
<c:set var="activeCards" value="active" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main-container" class="container">

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cardModal"><fmt:message
            key="jsp.create.card"/></button>

    <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Create new card</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="createCard" action="controller" method="post">
                    <input type="hidden" name="command" value="createCard"/>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name" class="col-form-label">New name card:</label>
                            <input name="name" class="form-control" type="text" id="name"/><br/>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-success" value="<fmt:message key="jsp.accept"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <br>
    <form id="sorting_cards" action="controller" method="post">
        <input type="hidden" name="command" value="userShowCards"/>
        <select name="sorting">
            <option value="name"><fmt:message key="jsp.sorting.name"/></option>
            <option value="number"><fmt:message key="jsp.sorting.number"/></option>
            <option value="money"><fmt:message key="jsp.sorting.money"/></option>
        </select>
        <select name="order">
            <option value="ascending"><fmt:message key="jsp.sorting.ascending"/></option>
            <option value="descending"><fmt:message key="jsp.sorting.descending"/></option>
        </select>
        <select name="filter">
            <option value="all"><fmt:message key="jsp.sorting.all"/></option>
            <option value="active"><fmt:message key="jsp.sorting.active"/></option>
            <option value="block"><fmt:message key="jsp.sorting.block"/></option>
        </select>
        <input type="submit" value="<fmt:message key="jsp.sort"/>">
    </form>
    <br>
    <table id="cards_table" class="table table-hover table-striped table-responsive">
        <thead>
        <tr>
            <td>#</td>
            <td><fmt:message key="jsp.Name"/></td>
            <td><fmt:message key="jsp.number"/></td>
            <td><fmt:message key="jsp.money"/></td>
            <td><fmt:message key="jsp.activity"/></td>
            <td class="content center"><fmt:message key="jsp.activity"/></td>
        </tr>
        </thead>

        <c:set var="k" value="0"/>
        <c:forEach var="card" items="${cards}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
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
                <td class="content center">
                    <c:if test="${card.requestId == 0}">
                    <c:if test="${card.activityId == 0}">
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                        <fmt:message key="jsp.block"/>
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel2" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <div class="spinner-border text-primary" role="status">
                                        <span class="sr-only">Loading...</span>
                                    </div>
                                    <h5 id="exampleModalLabel2" class="modal-title text-uppercase">&nbsp; Blocking card</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="modal-body">
                                        <p><fmt:message key="jsp.are_you_confirm_your_action"/></p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="blockUserCard"/>
                                            <input type="hidden" name="card_id" value="${card.id}"/>
                                            <input class="btn btn-danger" type="submit"
                                                   value="<fmt:message key="jsp.block"/>"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        </c:if>
                        <c:if test="${card.activityId == 1}">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2">
                            <fmt:message key="jsp.unblock.request"/>
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel3" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div class="spinner-border text-primary" role="status">
                                            <span class="sr-only">Loading...</span>
                                        </div>
                                        <h5 align="center" id="exampleModalLabel3" class="modal-title text-uppercase">&nbsp; Request for
                                            administrator</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="modal-body">
                                            <p><fmt:message key="jsp.are_you_confirm_your_action"/></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                            </button>
                                            <form action="controller" method="get">
                                                <input type="hidden" name="command" value="requestUserCardUnblock"/>
                                                <input type="hidden" name="card_id" value="${card.id}"/>
                                                <input class="btn btn-success" type="submit"
                                                       value="<fmt:message key="jsp.unblock.request"/>"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                            </c:if>
                            <c:if test="${card.requestId == 1}">
                            Under consideration
                            </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
