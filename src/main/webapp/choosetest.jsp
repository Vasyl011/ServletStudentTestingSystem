<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.takeTest.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body class="hm-gradient">

<%@ include file="menu.jspf" %><br/>

<div class="container">
    <c:if test="${message ne null}">
    <div class="alert alert-dismissible alert-${type}">
        <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
        <span>${message}</span>
    </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>

    <form action="${pageContext.request.contextPath}/choosetest" method="POST">
        <div class="col-6">
            <button type="submit" class="btn btn-warning"><fmt:message key="test.takeTest.sortButton"/></button>
            <h5>
                <select name="sort">
                <option>Subject Name</option>
                <option>Complexity</option>
                </select>
            </h5>
        </div>

        <h1><fmt:message key="test.all.table.title"/></h1>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="test.all.table.subjectname"/></th>
                <th><fmt:message key="test.all.table.complexity"/></th>
                <th><fmt:message key="test.all.table.duration"/></th>
                <th colspan="3"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>${test.getSubjectName()}</td>
                    <td>${test.getComplexity()}</td>
                    <td>${test.getDuration()}</td>

                    <td class="text-center">
                        <button type="button" class="btn btn-success"
                            data-toggle="modal" data-target="#startTestModal"
                            data-action="${pageContext.request.contextPath}/choosetest?testId=${test.getTestId()}">
                        <fmt:message key="test.takeTest.startTestButton"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </form>

</div>
<div class="modal fade" id="startTestModal" tabindex="-1" role="dialog" aria-labelledby="startModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="startModalLabel"><fmt:message key="test.takeTest.startTestButton"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&#215;</span>
                </button>
            </div>
            <div class="modal-body">
                <h5><fmt:message key="test.takeTest.start.question"/></h5>
                <form id="startTestForm" action="" method="POST"></form>
            </div>
            <div class="modal-footer">
                <a href="#" id="btnYes" class="btn confirm"><fmt:message key="button.yes"/></a>
                <a href="#" data-dismiss="modal"
                   aria-hidden="true" class="btn secondary"><fmt:message key="button.no"/></a>
            </div>
        </div>
    </div>

</div>

<script>
    $('#startTestModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let action = button.data('action');
        $('#startTestForm').attr('action', action);
    });
    $('#btnYes').click(function() {
        $('#startTestForm').submit().after();
        $('#startTestModal').modal('hide');
    });
</script>

</body>
</html>