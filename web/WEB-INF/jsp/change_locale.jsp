<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<fmt:setLocale value="${param.locale}" scope="session"/>

<fmt:setBundle basename="resources"/>

<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<jsp:forward page="settings.jsp"/>