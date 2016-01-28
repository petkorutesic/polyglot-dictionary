<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<html>

<head>
<title>Word Meaning Links</title>

<jsp:include page="../fragments/staticFiles.jsp"/>
</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Word meaning</span>
			</div>
			<table class="table table-hover">
				<tr>
					<td><a
						href="<c:url value='/words/${newWordLink.meaningFrom.word.id}' />">
							${newWordLink.meaningFrom.word.content}</a></td>
					<th width="100"></th>
					<td><c:out value="${newWordLink.meaningFrom.explanation}" /></td>
					<th width="100"></th>
				</tr>
			</table>

			<div class="table-container">
				<div class="well lead">Current links</div>
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th width="400"></th>
							<th width="100"></th>
							<th width="100"></th>
							<th width="100"></th>
							<th width="100"></th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty newWordLink.meaningFrom.fromLinks}">
							<c:forEach items="${newWordLink.meaningFrom.fromLinks}"
								var="link">
								<tr>
									<td>${link.meaningTo.explanation}</td>

									<td><a
										href="<c:url value='/wordlinks/edit-wordlink-${link.id}' />"
										class="btn btn-success custom-width">edit</a></td>
									<td><a
										href="<c:url value='/wordlinks/delete-wordlink-${link.id}' />"
										class="btn btn-danger custom-width">delete</a></td>
									<td><a
										href="<c:url value='/words/${link.meaningTo.word.id}' />">
											'${link.meaningTo.word.content}'</a></td>
									<td><a
										href="<c:url value='/wordlinks/meaning-${link.meaningTo.id}' />"
										class="btn btn-custom custom-width">Links</a></td>
								</tr>
								<tr>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="well">
				<div class="well lead">Insert new word link</div>
				<form:form method="POST" modelAttribute="newWordLink"
					class="form-horizontal">
					<form:input type="hidden" path="id" id="id" />

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="linkType">Type
								of link</label>
							<div class="col-md-7">
								<form:select path="linkType" items="${allLinkTypes}"
									multiple="false" itemValue="id" itemLabel="name"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="linkType" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="meaningTo">Related
								to meaning</label>
							<div class="col-md-7">
								<form:select path="meaningTo" items="${possibleMeaningsTo}"
									multiple="single" itemValue="id" itemLabel="explanation"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="meaningTo" class="help-inline" />
								</div>

							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" /> or <a
										href="<c:url value='/wordslist' />">Cancel</a>
								</c:when>
								<c:otherwise>
									<input type="submit" value="Save"
										class="btn btn-primary btn-sm" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</form:form>

			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
