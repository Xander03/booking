<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 23.11.2018
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/templates/header.jsp"%>
    <script type="module" src="/js/user/signUp.js"></script>

    <div class="sign-form-container">
        <div class="sign-form">
            <p class="sign-form__title"><fmt:message key="sign_up"/></p>
            <p class="form__error" id="error"></p>
            <form id="form">
                <div class="form-group">
                    <input id="login" type="text" required>
                    <label for="login"><fmt:message key="login" /></label>
                </div>

                <div class="form-group">
                    <input id="password" type="password" required>
                    <label for="password"><fmt:message key="password"/></label>
                </div>

                <button id="submit" type="submit" class="button">
                    <span><fmt:message key="sign_up"/></span>
                </button>
            </form>
        </div>
        <a href="" id="link"><fmt:message key="than_sign_in"/></a>
    </div>


<%@ include file="/WEB-INF/jsp/templates/footer.jsp"%>