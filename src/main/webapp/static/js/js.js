var ngApp = angular.module('app', []);

ngApp.controller('ctrl',["$scope","$attrs", "$http", function($scope, $attrs, $http){
    $scope.wordDto = [];
    $scope.searchWord = [];

    $scope.addMeaning = function() {
        $scope.wordDto.meanings.push({
            explanation: "",
            wordUsages: []
        });
    };

    $scope.loadData = function(searchString)
        {
            $scope.searchWord = JSON.parse(searchString);
        };
    
    $scope.addWordUsage = function(meaningDto) {
        meaningDto.wordUsages.push({
            name: "",
            studentDtoList: []
        });
    };
    
    $scope.addStudent = function(courseDto) {
        courseDto.studentDtoList.push({
            name: "",
            gender: ""
        });
    };
    
    $scope.getModel = function() {
        //copy the list (not necessary, just for demo purposes)

        var req = {
            method: 'GET',
            url: $attrs.contextPath  + "/get",
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({searchWordSPARQL: $scope.searchWord})
        };

        $http(req).
            success(function(data, status, headers, config){
                console.log(data);
                $scope.wordDto = data;
            }).
            error(function(jqXHR, textStatus, errorThrown){
                alert('Woops something wen wrong with the AJAX call');
            });
    };
    
    $scope.sendModel = function() {
        //copy the list (not necessary, just for demo purposes)
        var copyOfDepartmentlist = $scope.departmentDtoList.slice();
        
        //now clear the origninal list, the UI will update (not necessary, just for demo purposes)
        $scope.departmentDtoList = [];
        
        var req = {
            method: 'POST',
            url: $attrs.contextPath  + "/save",
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({departmentList: copyOfDepartmentlist})
        };

        alert('now sending updated model to the server');
        setTimeout(function(){ //timeout (not necessary, just for demo purposes)
            $http(req).
                success(function(data, status, headers, config){
                    $scope.wordDto = data;
                }).
                error(function(jqXHR, textStatus, errorThrown){
                    alert('Woops something wen wrong with the AJAX call');
                });
        },1000);
    };
    
    //do this when the page loads and the DOM is ready
    angular.element(document).ready(function () {
        debugger;
        alert('Page loaded, now fetching JSON from the server');
        //$scope.getModel();
    });
    
}]);
