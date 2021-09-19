<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.all.title"/></title>
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

            <h1><fmt:message key="test.all.table.title"/></h1>
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
<%--                    <th><fmt:message key="test.all.table.id"/></th>--%>
                    <th><fmt:message key="test.all.table.subjectname"/></th>
                    <th><fmt:message key="test.all.table.complexity"/></th>
                    <th><fmt:message key="test.all.table.duration"/></th>
<%--                    <th><fmt:message key="test.all.table.number_of_questions"/></th>--%>
                    <th colspan="3"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="test" items="${tests}">
                    <tr>
<%--                        <td>${test.getTestId()}</td>--%>
                        <td>${test.getSubjectName()}</td>
                        <td>${test.getComplexity()}</td>
                        <td>${test.getDuration()}</td>
<%--                        <td>${test.getNumberOfQuestions()}</td>--%>


                        <form action="${pageContext.request.contextPath}/testslist?editId=${test.getTestId()}" method="post">
                            <td ><div><button type="submit" class="btn btn-outline-primary"><fmt:message key="test.all.editButton"/></button></div></td>
                        </form>

                        <td class="text-center">
                            <button type="button" class="btn btn-outline-danger delete-test-btn"
                                    data-toggle="modal" data-target="#deleteTestModal"
                                    data-action="${pageContext.request.contextPath}/testslist?deleteId=${test.getTestId()}"  method="post">
                                <fmt:message key="test.all.deleteButton"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/createtest"
               class="btn btn-primary"><fmt:message key="test.all.table.createButton"/></a>
        </div>

        <div class="modal fade" id="deleteTestModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel"><fmt:message key="test.delete.title"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&#215;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h3><fmt:message key="test.delete.header"/></h3>
                        <p><fmt:message key="test.delete.question"/></p>
                        <form id="deleteTestForm" action="" method="POST"></form>
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
    $('#deleteTestModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let action = button.data('action');
        $('#deleteTestForm').attr('action', action);
    });
    $('#btnYes').click(function() {
        $('#deleteTestForm').submit().after();
        $('#deleteTestModal').modal('hide');
    });
</script>

<footer role="contentinfo" class="footer">
    <div class="container">
        <span class="text-muted">© Vasyl Stasyk, 2021</span>
    </div>
</footer>

</body>

</html>