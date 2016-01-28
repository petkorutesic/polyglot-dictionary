<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title>Languages List</title>
	<jsp:include page="../fragments/staticFiles.jsp"/>
</head>

<body>

    <jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">
	    <div class="well lead">List of languages</div>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
			<table class="table table-hover table-striped">
	    		<thead>
		      		<tr>
				        <th>Language</th>
				        <th width="100"></th>
				        <th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${languages}" var="l">
					<tr>
						<td>${l.lang}</td>
						<td><a href="<c:url value='/edit-lang-${l.lang}' />" class="btn btn-success custom-width">edit</a></td>
						<td><a href="<c:url value='/words/words-inlang-${l.id}' />" class="btn btn-success custom-width">words</a></td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
	 	<div class="well">
	 		<a href="<c:url value='/newlanguage' />">Add New Language</a>
	 	</div>
   	</div>
</body>
</html>
