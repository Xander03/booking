<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 29.11.2018
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp"%>

    <script type="module" src="/js/hotel/target.js"></script>
    <script type="module" src="/js/room/list.js"></script>

    <div class="target-container">
        <p id="error" class="target__error"></p>
        <div id="hotel" class="target">

            <div class="button-group">
                <button id="add_room" class="button button-group__button">
                    <span><fmt:message key="add_room"/></span>
                </button>
                <button id="edit" class="button button-group__button">
                    <span><fmt:message key="edit"/></span>
                </button>
                <button id="delete" class="button button-group__button">
                    <span><fmt:message key="delete"/></span>
                </button>
            </div>

            <div class="target__field">
                <label class="field-name">Id: </label>
                <label id="id"></label>
            </div>

            <div class="target__field">
                <label class="field-name"><fmt:message key="hotel_name"/>: </label>
                <label id="name"></label>
            </div>

            <div class="target__field">
                <label class="field-name"><fmt:message key="address"/>: </label>
                <label id="address"></label>
            </div>

            <div id="rooms" class="small-list"></div>

        </div>
    </div>

<%@include file="../templates/footer.jsp"%>