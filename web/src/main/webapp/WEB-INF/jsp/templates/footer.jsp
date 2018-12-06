<%--
  Created by IntelliJ IDEA.
  User: y700
  Date: 06.11.2018
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    </div>
    <div class="footer">
        <form>
            <div class="select">
                <select name="language" class="select-text" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
                <span class="select-highlight"></span>
                <span class="select-bar"></span>
            </div>
            <input id="id" name="id" hidden/>
        </form>
    </div>
    <script type="module" src="/js/user/language.js"></script>
</body>
</html>