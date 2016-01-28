<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<html>

<head>
    <title>Words List</title>
    <jsp:include page="../fragments/staticFiles.jsp"/>
    <link rel="stylesheet" type="text/css" href="${cp}/static/css/site.css" />
    <script src="${cp}/static/js/lib/angular.min.js" type="text/javascript"></script>
    <script src="${cp}/static/js/js.js"></script>
</head>
<body ng-app="app" ng-init="$scope.string = ${searchWordSPARQL.content}">
    {{loadData('${searchWordSPARQL}')}}
    {{getModel()}}
    <h4>Spring 4 Web MVC via Annotations</h4>
    <hr/>
    <p>
        <a href="http://www.outbottle.com"><c:out value="${searchWordSPARQL.content}" /></a>
    </p>

    <div>
        <div ng-controller="ctrl" context-path="${cp}">
            <p>
                <button ng-click="sendModel()">Send To Server</button>
            </p>

            <section class="meaning" ng-repeat="meaningDto in wordDto.meanings">
                <label>
                    <span>Explanation</span>
                    <input type="text" ng-model="meaningDto.explanation" />
                </label>

             <!--   <section class="explanation" ng-repeat="exDto in departmentDto.courseDtoList">
                    <label>
                        <span>Course Name</span>
                        <input type="text" ng-model="courseDto.name" />
                    </label>

                    <section class="student" ng-repeat="studentDto in courseDto.studentDtoList">
                        <label>
                            <span>Student Name</span>
                            <input type="text" ng-model="studentDto.name" />
                        </label>
                        <label>
                            <span>Student Gender</span>
                            <input class="short" type="text" ng-model="studentDto.gender" />
                        </label>
                    </section>
                    <button ng-click="addStudent(courseDto)">Add student</button>

                </section>
                <button ng-click="addCourse(departmentDto)">Add course</button>  */
            -->
            </section>
            <button ng-click="addMeaning()">Add meaning</button>
        </div>
    </div>

</body>
</html>
