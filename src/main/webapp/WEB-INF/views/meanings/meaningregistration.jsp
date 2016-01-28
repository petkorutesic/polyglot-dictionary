<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Meaning Registration Form</title>
<jsp:include page="../fragments/staticFiles.jsp"/>
</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">
		<div class="well lead">Insert meaning for
		<br>
		<a href="<c:url value='/words/${newMeaning.word.id}' />">${newMeaning.word.content}</a> &nbsp;
		</div>
		<form:form method="POST" modelAttribute="newMeaning"
			class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="explanation">Explanation</label>
					<div class="col-md-7">
						<form:input type="text" path="explanation" id="explanation"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="explanation" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="wordTypes"></label>
					<div class="col-md-7">
						<form:select path="wordTypes" items="${possibleWordTypes}"
							multiple="true" itemValue="id" itemLabel="text"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="wordTypes" class="help-inline" />
						</div>

					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="language">Language</label>
					<div class="col-md-7">
						<form:select path="language" items="${formLanguage}" multiple="false" itemValue="id" itemLabel="lang" 
						  class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="language" class="help-inline"/>
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
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/wordslist' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
