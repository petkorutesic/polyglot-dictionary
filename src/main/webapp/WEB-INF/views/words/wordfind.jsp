<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find word</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<spring:url value="/words" var="formUrl" />
			<form:form modelAttribute="currentWord" action="${fn:escapeXml(formUrl)}"
				method="get" class="form-horizontal" id="search-word-form">
					<div class="row">
				<fieldset>
			
					<div class="control-group" id="content">
						<label class="control-label">Word </label>
						<form:input path="content" size="30" maxlength="80" />
						<span class="help-inline"><form:errors path="*" /></span>
					</div>
					<div class="form-actions">
						<button type="submit">Find word</button>
					</div>
				
				</fieldset>
				</div>
			</form:form>

			<!-- Default panel contents -->
		</div>
		<div class="well">
			<a href="<c:url value='/words/newword-inlang-${language.id}' />">Add
				New Word</a>
		</div>
	</div>
</body>
</html>
