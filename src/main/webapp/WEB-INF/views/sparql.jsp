<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link href="<c:url value='/static/css/site.css' />" rel="stylesheet"></link>
<jsp:include page="fragments/staticFiles.jsp"/>
<title>Word Sparql search</title>

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

<body ng-app="myApp" class="ng-cloak">
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <div ui-view></div>

    <jsp:include page="fragments/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="<c:url value='/static/js/lib/angular-ui-router.js' />"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/service/worddto_service.js' />"></script>
    <script src="<c:url value='/static/js/service/wordsearch_object.js' />"></script>
    <script src="<c:url value='/static/js/controller/worddto_controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/wordsearch_controller.js' />"></script>
</body>
</html>