<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
<jsp:include page="../fragments/staticFiles.jsp"/>

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
				$('#fromDate').datepicker({
			        dateFormat: 'dd/mm/yy',
			        onSelect: function(datetext){
			            var d = new Date(); // for now
			            datetext=datetext+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
			            $('#fromDate').val(datetext);
			        },
			    });

				$('#untilDate').datepicker({
					dateFormat: 'dd/mm/yy',
					onSelect: function(datetext){
						var d = new Date(); // for now
						datetext=datetext+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
						$('#untilDate').val(datetext);
					},
				});

			});
</script>


<body>
    <jsp:include page="../fragments/bodyHeader.jsp"/>
	<div class="generic-container">
	    <div class="well lead">Word search</div>
		<div class="panel panel-default">
			<spring:url value="/words" var="formUrl" />
			<form:form modelAttribute="searchWord" action="${fn:escapeXml(formUrl)}"
				method="get" class="form-horizontal" id="search-word-form">
                <div class = "row">
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
                        <label class="col-md-3 control-lable" for="searchMode">Language</label>
                        <div class="col-md-7">
                            <form:select path ="searchMode" class="form-control input-sm" >
                                <form:options items="${searchModels}" />
                            </form:select>
                            <div class="has-error">
                                <form:errors path="searchMode" class="help-inline" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="numberOfVisits">Number or visits</label>
						<div class="col-md-7">
							<form:input type="date" path="numberOfVisits" id="numberOfVisits"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="numberOfVisits" class="help-inline" />
							</div>
						</div>
					</div>
				</div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="fromDate">Accessed from</label>
                        <div class="col-md-7">
                            <form:input type="text" path="fromDate" id="fromDate"
                                class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="fromDate" class="help-inline" />
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="untilDate">Accessed until</label>
						<div class="col-md-7">
							<form:input type="text" path="untilDate" id="untilDate"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="untilDate" class="help-inline" />
							</div>

						</div>
					</div>
				</div>

                <div class="row">
                    <div class="form-actions floatRight">
					<input type="submit" value="Search" class="btn btn-primary btn-sm" />
                    </div>
                </div>
			</form:form>

		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
