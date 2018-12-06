<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 29.11.2018
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp"%>

    <script type="module" src="/js/room/target.js"></script>

    <div class="target-container">
        <p id="error" class="target__error"></p>
        <div id="room" class="target">

            <div class="button-group">
                <button id="edit" class="button button-group__button">
                    <span><fmt:message key="edit"/></span>
                </button>
                <button id="delete" class="button button-group__button">
                    <span><fmt:message key="delete"/></span>
                </button>
                <button id="reserve" class="button button-group__button">
                    <span><fmt:message key="reserve"/></span>
                </button>
                <button id="unreserve" class="button button-group__button">
                    <span><fmt:message key="unreserve"/></span>
                </button>
            </div>

            <div class="target__field">
                <label class="field-name">Id</label>
                <label id="id"></label>
            </div>

            <div class="target__field">
                <label class="field-name"><fmt:message key="hotel"/></label>
                <label id="hotel"></label>
            </div>

            <div class="target__field">
                <label class="field-name"><fmt:message key="floor"/></label>
                <label id="floor"></label>
            </div>

            <div class="target__field">
                <label class="field-name"><fmt:message key="places"/></label>
                <label id="places"></label>
            </div>
        </div>
    </div>

<%@include file="../templates/footer.jsp"%>