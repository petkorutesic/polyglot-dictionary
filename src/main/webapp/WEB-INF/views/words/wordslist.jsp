<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Words List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
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
							<td><a href="<c:url value='/words/${word.id}' />"
								class="btn btn-success custom-width">meanings</a></td>
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

	</div>
</body>
</html>
