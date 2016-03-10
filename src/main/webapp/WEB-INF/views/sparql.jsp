<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link href="<c:url value='/static/css/site.css' />" rel="stylesheet"></link>
<jsp:include page="fragments/staticFiles.jsp"/>
<title>Word Sparql search</title>

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
    <script src="<c:url value='/static/js/directive/word_complete.js' />"></script>
    <script src="<c:url value='/static/js/controller/wordsearch_controller.js' />"></script>
</body>
</html>