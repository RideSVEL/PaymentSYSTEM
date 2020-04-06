<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title><fmt:message key="jsp.payment"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="login-page">
    <h2 class="text-light text-uppercase"><fmt:message key="jsp.confirm.action"/>:</h2>
    <br>
    <div class="form-group">
        <form id="showCards" action="controller" method="post">
            <input type="hidden" name="command" value="userShowCards"/>
            <input class="form-control btn btn-danger btn-block shadow-lg" type="submit"
                   value="<fmt:message key="jsp.reject"/>"/>
        </form>
    </div>
    <div class="form-group">
        <button type="button" class="btn btn-warning btn-block shadow-lg" data-toggle="modal"
                data-target="#exampleModal3">
            <fmt:message key="jsp.payment.defer"/>
        </button>
        <div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel3" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="spinner-border text-primary" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                        <h5 id="exampleModalLabel3" class="modal-title text-uppercase text-dark">
                            &nbsp; <fmt:message key="jsp.confirm.defer"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="controller" method="post">
                        <div class="modal-body">
                            <div class="modal-body">
                                <div class="form-group">
                                    <p class="text-dark text-uppercase"><fmt:message key="jsp.enter_your_pass"/>: </p>
                                    <label for="password1"><fmt:message key="jsp.password"/></label>
                                    <input type="password" name="password" id="password1" class="form-control shadow-lg"
                                           placeholder="<fmt:message key="jsp.password"/>" required
                                           pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key="jsp.close"/>
                                </button>
                                <input type="hidden" name="command" value="confirmPayment"/>
                                <input type="hidden" name="confirm" value="false"/>
                                <input class="btn btn-success" type="submit"
                                       value="<fmt:message key="jsp.accept"/>"/>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <button type="button" class="btn btn-success btn-block shadow-lg" data-toggle="modal"
                data-target="#exampleModal">
            <fmt:message key="jsp.send.payment"/>
        </button>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel2" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="spinner-border text-primary" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                        <h5 id="exampleModalLabel2" class="modal-title text-uppercase text-dark">
                            &nbsp; <fmt:message key="jsp.confirm.send"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="controller" method="post">
                        <div class="modal-body">
                            <div class="modal-body">
                                <div class="form-group">
                                    <p class="text-dark text-uppercase"><fmt:message key="jsp.enter_your_pass"/>:</p>
                                    <label for="password"><fmt:message key="jsp.password"/></label>
                                    <input type="password" name="password" id="password" class="form-control shadow-lg"
                                           placeholder="<fmt:message key="jsp.password"/>" required
                                           pattern="[\w&#1072;-&#1103;&#1040;-&#1071;]{5,20}"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key="jsp.close"/>
                                </button>
                                <input type="hidden" name="command" value="confirmPayment"/>
                                <input type="hidden" name="confirm" value="true"/>
                                <input class="btn btn-success" type="submit"
                                       value="<fmt:message key="jsp.accept"/>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
