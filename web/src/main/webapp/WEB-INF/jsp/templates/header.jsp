<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 06.11.2018
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="view" />
<html>
<head>
    <script src="/js/libs/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="/css/all.css">
    <title><fmt:message key="title" /></title>
</head>
<body>
    <script type="module" src="/js/user/header.js"></script>
    <ul id="nav-bar" class="nav-bar">
        <li class="nav-bar__logo">Korzun</li>
        <li class="nav-bar__link">
            <a id="main_page" href=""><fmt:message key="main_page"/></a>
        </li>
        <li class="nav-bar__link">
            <a id="hotels_page" href=""><fmt:message key="hotels_page"/></a>
        </li>
        <li class="nav-bar__link">
            <a id="sign_out" href=""><fmt:message key="sign_out"/></a>
        </li>
    </ul>
    <div class="container">
