<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.create.form.title"/></title>
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

                <form action="${pageContext.request.contextPath}/createtest" method="POST">
                    <h2><fmt:message key="test.create.form.presentation.message"/></h2>
                    <c:if test="${errorMessage != null}">
                        <div style="color: red;">${errorMessage}</div>
                    </c:if>
                    <div class="form-group">
                        <label for="subjectName"><fmt:message key="test.create.form.subject"/></label>
                        <input type="text" class="form-control" id="subjectName" name="subjectName" required/>
                    </div>

                    <div class="form-group">
                            <label><fmt:message key="test.create.form.complexity"/>
                                 <select name="complexity">
                                    <option>easy</option>
                                    <option>medium</option>
                                    <option>hard</option>
                                </select>
                            </label>
                    </div>

                    <div class="form-group">
                        <label for="duration"><fmt:message key="test.create.form.duration"/></label>
                        <input type="number" min="1" max="120" class="form-control" id="duration" name="duration" required />
                    </div>

                    <div class="row mt-3">
                        <div class="col-6">
                            <button type="submit" class="btn btn-success btn-block"><fmt:message key="test.create.form.createButton"/></button>
                        </div>
                        <a href="${pageContext.request.contextPath}/testslist" class="btn btn-block btn-secondary col-6">
                            <fmt:message key="test.create.backButton"/>
                        </a>
                    </div>

                </form>
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