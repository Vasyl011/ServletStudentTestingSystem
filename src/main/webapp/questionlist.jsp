<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.question.all.title"/></title>
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

            <h1><fmt:message key="test.question.all.title"/></h1>
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="test.question.all.title.table.testName"/></th>
                    <th><fmt:message key="test.question.all.title.table.questionText"/></th>
                    <th><fmt:message key="test.question.all.title.table.questionOptions"/></th>
                    <th colspan="2"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="question" items="${questions}">
                    <tr>
                        <td>${question.getTest().getSubjectName()}</td>
                        <td>${question.getQuestionText()}</td>
                        <td>
                            <c:forEach var="option" items="${question.getOptions()}">
                                <p>${option.getOptionCorrect()? '✅': '❌'} ${option.getOptionText()}</p>
                            </c:forEach>
                        </td>
                        <td class="text-center">
                            <button type="button" class="btn btn-outline-danger delete-subject-btn"
                                    data-toggle="modal" data-target="#deleteQuestionModal"
                                    data-action="${pageContext.request.contextPath}/questionlist?deleteId=${question.getQuestionId()}"  method="post">
                                <fmt:message key="test.question.all.table.deleteButton"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/question" class="btn btn-primary">
                <fmt:message key="test.question.all.table.addButton"/>
            </a>
        </div>

        <div class="modal fade" id="deleteQuestionModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel"><fmt:message key="test.question.delete.title"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&#215;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h3><fmt:message key="test.question.delete.header"/></h3>
                        <p><fmt:message key="test.question.delete.question"/></p>
                        <form id="deleteQuestionForm" action="" method="POST"></form>
                    </div>
                    <div class="modal-footer">
                        <a href="#" id="btnYes" class="btn confirm"><fmt:message key="button.yes"/></a>
                        <a href="#" data-dismiss="modal"
                           aria-hidden="true" class="btn secondary"><fmt:message key="button.no"/></a>
                    </div>
                </div>
            </div>


        </div>
    </div>
</main>


<script>
    $('#deleteQuestionModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let action = button.data('action');
        $('#deleteQuestionForm').attr('action', action);
    });
    $('#btnYes').click(function() {
        $('#deleteQuestionForm').submit().after();
        $('#deleteQuestionModal').modal('hide');
    });
</script>

<footer role="contentinfo" class="footer">
    <div class="container">
        <span class="text-muted">© Vasyl Stasyk, 2021</span>
    </div>
</footer>

</body>

</html>