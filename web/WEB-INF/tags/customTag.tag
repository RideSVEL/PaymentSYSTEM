<%@ tag import="java.util.Date, java.text.DateFormat" %>

<%
    Date now = new Date();
    DateFormat dfm = DateFormat.getDateInstance(DateFormat.FULL);
    out.println(dfm.format(now));
%>