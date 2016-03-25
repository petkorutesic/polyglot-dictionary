<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Word Meaning Links</title>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<script type="text/javascript" src="/static/js/wordlinks/wordlink.js"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				//attach autocomplete
				$("#wordQuery").autocomplete(
						{
							minLength : 1,
							delay : 500,

							//define callback to format results 
							source : function(request, response) {
								$.getJSON("/words/searchword_list", request,
										function(result) {
											//alert(request); //debug
											response($.map(result, function(
													item) {
												return {
													// following property gets displayed in drop down
													label : item.content,
													// following property gets entered in the textbox
													value : item.id
												}

											}));
										});
							},

							//define select handler
							select : function(event, ui) {
								if (ui.item) {
									event.preventDefault();
									$("#selected_ span").append(
											'<a href=' + ui.item.id + ' class="btn-metadata2" target="_blank">'
													+ ui.item.label + '</a>');
									//$("#tagQuery").value = $("#tagQuery").defaultValue
									// 									var defValue = $("#wordQuery").prop(
									// 											'defaultValue');
									$("#wordQuery").val(ui.item.label);
									$("#wordIdValue").val(ui.item.value);
									$("#wordQuery").blur();
									return false;
								}
							},

							focus : function(event, ui) {
								event.preventDefault();
								$("#wordQuery").val(ui.item.label);
								//$("#wordIdValue").val(ui.item.id);
								return false;
							}

						});

			});

	function call_insert_link_page() {
		var wordId = $("#wordIdValue").val();
		var meaningId = "${fromMeaning.id}";

		if (wordId != undefined && wordId != null) {
			window.location = '/wordlinks/meaning-' + meaningId + '/word-'
					+ wordId;
		}

	}
</script>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Word meaning</span>
			</div>
			<table class="table table-hover">
				<tr>
					<td><a href="<c:url value='/words/${fromMeaning.word.id}' />">${fromMeaning.word.content}
					</a></td>
					<th width="100"></th>
					<td><c:out value="${fromMeaning.explanation}" /></td>
					<th width="100"></th>
				</tr>
			</table>

			<div id="all_tags" class="well">
				<div class="well lead">Search for a word with the related
					meaning</div>
				<input name="wordQuery" id="wordQuery"
					 class="form-control input-sm ui-autocomplete-input ui-corner-all" 
					placeholder="<spring:message code="find.keyword" text="Type word..."/>">
				<input type="hidden" id="wordIdValue" name="wordIdValue" />
				
				 <input
					type="submit" value="Load meanings"
					onclick="call_insert_link_page()" />

			</div>

			<div class="table-container">
				<div class="well lead">Current links</div>
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th width="400"></th>
							<th width="100"></th>
							<th width="100"></th>
							<th width="100"></th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty fromMeaning.fromLinks}">
							<c:forEach items="${fromMeaning.fromLinks}" var="link">
								<tr>
									<td>${link.meaningTo.explanation}</td>

									<td><a
										href="<c:url value='/wordlinks/edit-wordlink-${link.id}' />"
										class="btn btn-success custom-width">edit</a></td>
									<td><a
										href="<c:url value='/wordlinks/meaning-${fromMeaning.id}/delete-wordlink-${link.id}' />"
										class="btn btn-danger custom-width">delete</a></td>
									<td><a
										href="<c:url value='/words/${link.meaningTo.word.id}' />">
											${link.meaningTo.word.content}</a></td>
								</tr>
								<tr>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>


		</div>
	</div>
</body>
</html>
