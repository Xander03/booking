<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 29.11.2018
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp"%>

    <script type="module" src="/js/security/isAdmin.js"></script>
    <script type="module" src="/js/room/form.js"></script>

    <div class="form-container">
        <p id="error" class="form__error"></p>
        <form id="form" class="form">

            <div class="form-group">
                <input id="id" type="text" readonly/>
                <label for="id">Id</label>
            </div>

            <div class="form-group">
                <input id="hotel" type="text" readonly required/>
                <label for="hotel"><fmt:message key="hotel"/></label>
            </div>

            <div class="form-group">
                <input id="floor" type="number" required/>
                <label for=floor><fmt:message key="floor"/></label>
            </div>

            <div class="form-group">
                <input id="places" type="number" required/>
                <label for="places"><fmt:message key="places"/></label>
            </div>

            <button id="submit" type="submit" class="button">
                <span><fmt:message key="save"/></span>
            </button>
        </form>
    </div>

<%@include file="../templates/footer.jsp"%>