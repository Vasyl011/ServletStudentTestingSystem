<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="personaloffice.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="menu.jspf" %>

<main role="main">
    <div class="jumbotron">
        <div class="container">
            <c:if test="${message ne null}">
                <div class="alert alert-dismissible alert-${type}">
                    <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
                    <span>${message}</span>
                </div>
            </c:if>
            <c:remove var="message" scope="session"/>
            <c:remove var="type" scope="session"/>

            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="personaloffice.testname"/></th>
                    <th><fmt:message key="personaloffice.result"/></th>
                    <th><fmt:message key="personaloffice.testtime"/></th>
                    <th colspan="3"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="studentTest" items="${studentTests}">
                    <tr>
                        <td>${studentTest.getTest().getSubjectName()}</td>
                        <td>${studentTest.getResult()}%</td>
                        <td>${f:formatLocalDateTime(studentTest.getActualEndTestTime(), "mm:ss")}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</main>


<footer role="contentinfo" class="footer">
    <div class="container">
        <span class="text-muted">© Vasyl Stasyk, 2021</span>
    </div>
</footer>

</body>

</html>