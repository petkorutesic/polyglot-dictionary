<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<title>Words List</title>
<jsp:include page="../fragments/staticFiles.jsp"/>
</head>

<body>
    <jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">
	    <div class="well lead">Word list</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of words </span>
			</div>
			<div class="well">
				<a href="<c:url value='/words/newword-inlang-${language.id}' />">Add
					New word</a>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Word</th>
						<th width="100">Language</th>
						<th width="100"></th>
						<th width="100"></th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${words}" var="word">
						<tr>
							<td><spring:url value="/words/{wordId}" var="wordUrl">
									<spring:param name="wordId" value="${word.id}" />
								</spring:url> <a href="${fn:escapeXml(wordUrl)}"> <c:out
										value="${word.content}" />
							</a></td>
							<td>${word.language.lang}</td>
							<td><a href="<c:url value='/words/${word.id}' />"
								class="btn btn-success custom-width" target="_blank">meanings</a></td>
							<td><a href="<c:url value='/words/edit-word-${word.id}' />"
								class="btn btn-success custom-width">edit</a></td>
							<td><a
								href="<c:url value='/words/delete-word-${word.id}' />"
								class="btn btn-danger custom-width">delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panel-heading">
			<span class="lead">${fn:length(words)} rows </span>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
