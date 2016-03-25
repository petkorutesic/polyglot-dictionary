<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>

<title>Words List</title>
<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
    <jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">
        <div class="well lead">Word Visit Log</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<table class="table table-hover">
				<tr>
					<td><c:out value="${currentWord.content}" /></td>
					<th width="100"></th>
					<td>${currentWord.language.lang}</td>
					<td><a
						href="<c:url value='/words/edit-word-${currentWord.id}' />"
						class="btn btn-success custom-width">edit</a></td>
					<td><a
						href="<c:url value='/words/delete-word-${currentWord.id}' />"
						class="btn btn-custom btn-danger custom-width">delete word</a></td>
                    <td><a
                        href="<c:url value='/wordlogs/delete-wordlogs-forword-${currentWord.id}' />"
                        class="btn btn-danger custom-width">delete log</a></td>
				</tr>

			</table>
			<div class="well">
				<a
					href="<c:url value='/meaning/new-meaning-forword-${currentWord.id}' />">Add
					New Meaning</a> <br /> <a
					href="<c:url value='/words/newword-inlang-${currentWord.language.id}' />">Add
					New word</a>
			</div>

			<div class="table-container">
				<h4>&nbsp;Visits</h4>
                <table class="table table-striped table-hover ">
                    <thead>
                        <tr>
                            <th width="400">Date</th>
                            <th width="100">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${wordLogs}" var="log">
                            <tr>
                                <td>${log.timeVisit}</td>
                                <td>
                                    <c:choose>
                                         <c:when test="${log.active == 1}">
                                             Active
                                         </c:when>
                                         <c:otherwise>
                                             Old
                                         </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
			</div>  <!-- end of pannel for visits -->

		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
