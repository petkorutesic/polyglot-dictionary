<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Word Registration Form</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<script type="text/javascript" src="/static/js/wordlinks/wordlink.js"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				//attach autocomplete
				$("#content").autocomplete(
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
									$("#content").val(ui.item.label);
									//$("#wordIdValue").val(ui.item.value);
									$("#content").blur();
									return false;
								}
							},

							focus : function(event, ui) {
								event.preventDefault();
								$("#content").val(ui.item.label);
								//$("#wordIdValue").val(ui.item.id);
								return false;
							}

						});
				$('#timeCreation').datepicker({
			        dateFormat: 'dd/mm/yy',
			        onSelect: function(datetext){
			            var d = new Date(); // for now
			            datetext=datetext+" "+d.getHours()+": "+d.getMinutes()+": "+d.getSeconds();
			            $('#timeCreation').val(datetext);
			        },
			    });

			});
</script>

<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

	<div class="generic-container">
		<div class="well lead">Word insert Form</div>
		<form:form method="POST" modelAttribute="currentWord"
			class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="content">Word</label>
					<div class="col-md-7">
						<form:input type="text" path="content" id="content"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="content" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="language">Language</label>
					<div class="col-md-7">
						<form:select path="language" items="${formLanguage}"
							multiple="false" itemValue="id" itemLabel="lang"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="language" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="timeCreation">Time</label>
					<div class="col-md-7">
						<form:input type="text" path="timeCreation" id="timeCreation" readonly="true"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="content" class="help-inline" />
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
							<input type="submit" value="Save" class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/wordslist' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
