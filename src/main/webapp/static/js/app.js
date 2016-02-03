'use strict';

var App = angular.module('myApp', ['ui.router']);


App.config(['$stateProvider', function ($stateProvider) {

//create route object

    var search= {
        url: '',
        templateUrl: '/static/sparqlwords/search.html',
        controller: 'WordSearchController'
    },
    wordDTO= {
        url: '/word',
        templateUrl: '/static/sparqlwords/word.html',
        controller: 'WordDtoController as wctrl'
    };

//Now add these route state privider

    $stateProvider
       .state('search', search)
       .state('wordDTO', wordDTO);
}]);

App.config(function ($urlRouterProvider) {
    //set default page to redirect; if wrong route is given redirect here
    $urlRouterProvider.otherwise('');
});



