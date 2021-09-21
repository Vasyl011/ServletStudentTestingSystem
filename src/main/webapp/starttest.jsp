<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.startTest.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="mainuser.jspf" %>

<div class="container">
    <c:if test="${message ne null}">
    <div class="alert alert-dismissible alert-${type}">
        <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
        <span>${message}</span>
    </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>

    <h1><fmt:message key="test.takeTest.title"/></h1>

    <c:forEach var="studentTest" items="${studentTests}">
        <tr>
            <p><fmt:message key="test.takeTest.start.startTime"/>: ${f:formatLocalDateTime(studentTest.getStartTestTime(), "HH:mm:ss dd-MM-yyyy")}</p>
            <p><fmt:message key="test.takeTest.start.endTime"/>: ${f:formatLocalDateTime(studentTest.getEndTestTime(), "HH:mm:ss dd-MM-yyyy")}</p>
        </tr>
    <form action="${pageContext.request.contextPath}/starttest?studentTestId=${studentTest.getStudentTestId()}" method="POST">

    <tbody>
        <div id="testQuestionsList">
            <c:forEach var="question" items="${questions}">
                <tr>
                <h5><td>${question.getQuestionText()}</td></h5>
                <td>
                    <c:forEach var="option" items="${question.getOptions()}">
                        <p> <input type="checkbox" id="optionId" value="${option.getOptionId()}" name="optionId">
                            <label class="form-check-label">${option.getOptionText()}</label></p>
                    </c:forEach>
                </td>
                </tr>
            </c:forEach>
        </div>
    </tbody>

        <div class="col-3">
            <button type="submit" class="btn btn-success btn-block"><fmt:message key="test.takeTest.start.finishTestButton"/></button>
        </div>
    </form>
    </c:forEach>

</div>




</body>

</html>