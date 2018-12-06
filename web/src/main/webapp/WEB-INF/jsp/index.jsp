<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 06.11.2018
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="templates/header.jsp"%>

    <script type="module" src="/js/security/preAuthorized.js"></script>
    <p id="error"></p>
    <div id="rooms"><fmt:message key="no_rooms_reserved"/></div>
    <script type="module" src="/js/user/rooms.js"></script>

<%@include file="templates/footer.jsp"%>
