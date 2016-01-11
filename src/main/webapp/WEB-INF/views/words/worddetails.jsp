<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<span class="lead">Word </span>
			</div>
			<table class="table table-hover">
				<tr>
					<td><c:out value="${currentWord.content}" /></td>
					<th width="100"></th>
					<td><a
						href="<c:url value='/words/edit-word-${currentWord.id}' />"
						class="btn btn-success custom-width">edit</a></td>
					<td><a
						href="<c:url value='/words/delete-word-${currentWord.id}' />"
						class="btn btn-danger custom-width">delete</a></td>
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
				<h4>&nbsp;Word meanings</h4>


				<c:if test="${! empty currentWord.wordMeanings}">
					<c:forEach items="${currentWord.wordMeanings}" var="meaning">

						<table class="table table-striped table-hover ">
							<thead>
								<tr>
									<th width="400"></th>
									<th width="100"></th>
									<th width="100"></th>
									<th width="100"></th>
									<th width="100"></th>
									<th width="100"></th>
									<th width="100"></th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${meaning.explanation}</td>
									<td><c:if test="${! empty meaning.wordTypes}">
											<c:forEach items="${meaning.wordTypes}" var="wordType">
												<a href="<c:url value='/wordtypes/${wordType.id}' />">
													${wordType.text}</a> &nbsp;
										</c:forEach>
										</c:if></td>

									<td><a
										href="<c:url value='/meaning/edit-meaning-${meaning.id}' />"
										class="btn btn-success custom-width">edit</a></td>
									<td><a
										href="<c:url value='/meaning/delete-meaning-${meaning.id}' />"
										class="btn btn-danger custom-width">delete</a></td>
									<td><a
										href="<c:url value='/wordusages/new-usage-for-meaning-${meaning.id}' />"
										class="btn btn-success custom-width">Examples</a></td>


									<td><c:if test="${! empty meaning.fromLinks}">
											<c:forEach items="${meaning.fromLinks}" var="wordlink">
												<a
													href="<c:url value='/words/${wordlink.meaningTo.word.id}' />">
													${wordlink.meaningTo.word.content}</a> &nbsp;
										</c:forEach>
										</c:if></td>
									<td><a
										href="<c:url value='/wordlinks/meaning-${meaning.id}/search' />"
										class="btn btn-custom custom-width">Links</a></td>
								</tr>
							</tbody>
						</table>

						<c:if test="${! empty meaning.wordUsages}">
							<table class="table table-hover">
								<c:forEach items="${meaning.wordUsages}" var="wordUsage">
									<tr>
										<td><a
											href="<c:url value='/wordusages/${wordUsage.id}' />">
												${wordUsage.text}</a> &nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</c:forEach>
				</c:if>

			</div>

		</div>
	</div>
</body>
</html>
