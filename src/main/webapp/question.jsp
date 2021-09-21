<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="test.question.add.title"/></title>
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


            <form action="${pageContext.request.contextPath}/question" method="POST">
                <h2><fmt:message key="test.question.form.presentation.message"/></h2>
                <c:if test="${errorMessage != null}">
                    <div style="color: red;">${errorMessage}</div>
                </c:if>
                <div class="form-group">
                    <label for="questionText"><fmt:message key="test.question.add.questionText"/></label>
                    <input type="text" class="form-control" id="questionText" name="questionText" required/>
                </div>

                <div class="form-group">
                    <label for="option1"><fmt:message key="test.option1.add.option.text"/></label>
                    <input type="text" class="form-control" id="option1" name="option1" required/>
                    <input type="checkbox" id="option1isCorrect" value="true" name="option1isCorrect">
                </div>

                <div class="form-group">
                    <label for="option2"><fmt:message key="test.option2.add.option.text"/></label>
                    <input type="text" class="form-control" id="option2" name="option2" required/>
                    <input type="checkbox" id="option2isCorrect" value="true" name="option2isCorrect">
                </div>

                <div class="form-group">
                    <label for="option3"><fmt:message key="test.option3.add.option.text"/></label>
                    <input type="text" class="form-control" id="option3" name="option3" required/>
                    <input type="checkbox" id="option3isCorrect" value="true" name="option3isCorrect">
                </div>

                <div class="form-group">
                    <label for="option4"><fmt:message key="test.option4.add.option.text"/></label>
                    <input type="text" class="form-control" id="option4" name="option4" required/>
                    <input type="checkbox" id="option4isCorrect" value="true" name="option4isCorrect">
                </div>

                <div class="row mt-3">
                    <div class="col-6">
                        <button type="submit" class="btn btn-success btn-block"><fmt:message key="test.question.add.addQuestionButton"/></button>
                    </div>
                    <a href="${pageContext.request.contextPath}/questionlist" class="btn btn-block btn-secondary col-6">
                        <fmt:message key="test.question.add.backButton"/>
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