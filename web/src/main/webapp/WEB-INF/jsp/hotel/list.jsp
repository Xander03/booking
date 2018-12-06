<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 29.11.2018
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp"%>

    <script type="module" src="/js/hotel/list.js"></script>
    <p id="error"></p>
    <button id="add"><fmt:message key="add_new"/></button>
    <div id="hotels">
        <fmt:message key="no_hotels"/>
    </div>

<%@include file="../templates/footer.jsp"%>