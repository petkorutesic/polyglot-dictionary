<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<html>

<head>
<title>Word Examples</title>
<script type="text/javascript" src="/static/js/wordlinks/wordlink.js"></script>
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
					<td><a href="<c:url value='/words/${meaning.word.id}' />">
							${meaning.word.content}</a></td>
					<th width="100"></th>
					<td><c:out value="${meaning.explanation}" /></td>
					<th width="100"></th>
				</tr>
			</table>

			<div class="table-container">
				<div class="well lead">Current usage examples</div>
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th width="600"></th>
							<th width="100"></th>
							<th width="100"></th>


						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty meaning.wordUsages}">
							<c:forEach items="${meaning.wordUsages}" var="usage">
								<tr>
									<td>${usage.text}</td>

									<td><a
										href="<c:url value='/wordusages/edit-wordusage-${usage.id}' />"
										class="btn btn-success custom-width">edit</a></td>
									<td><a
										href="<c:url value='/wordusages/delete-wordusage-${usage.id}' />"
										class="btn btn-danger custom-width">delete</a></td>
								</tr>
								<tr>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="well">
				<div class="well lead">Insert new word example</div>
				<form:form method="POST" modelAttribute="newUsage"
					class="form-horizontal">
					<form:input type="hidden" path="id" id="id" />

<!-- 					<div class="row"> -->
<!-- 						<div class="form-group col-md-12"> -->
<!-- 							<label class="col-md-3 control-lable" for="usageTypes">Type -->
<!-- 								of example</label> -->
<!-- 							<div class="col-md-7"> -->
<%-- 								<form:select path="usageTypes" items="${allUsageTypes}" --%>
<%-- 									multiple="true" itemValue="id" itemLabel="name" --%>
<%-- 									class="form-control input-sm" /> --%>
<!-- 								<div class="has-error"> -->
<%-- 									<form:errors path="linkType" class="help-inline" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="text">Example of use</label>
							<div class="col-md-7">
								<form:input type="text" path="text" id="text"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="text" class="help-inline" />
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
