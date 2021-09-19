<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="userlist.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="main.jspf" %>

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


            <h1><fmt:message key="userlist.title"/></h1>
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="userlist.uesrname"/></th>
                    <th><fmt:message key="userlist.blocked"/></th>
                    <th colspan="2"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.getUsername()}</td>
                        <td>${user.isBlocked()? 'BLOCKED': 'UNBLOCKED'}</td>

                        <form action="${pageContext.request.contextPath}/userlist?unblocked=${user.getId()}" method="post">
                            <td ><div><button type="submit" class="btn btn-primary"><fmt:message key="userlist.unblockedButton"/></button></div></td>
                        </form>

                        <form action="${pageContext.request.contextPath}/userlist?blocked=${user.getId()}" method="post">
                            <td ><div><button type="submit" class="btn btn-danger"><fmt:message key="userlist.blockedButton"/></button></div></td>
                        </form>

                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>


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