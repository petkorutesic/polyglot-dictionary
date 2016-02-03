'use strict';

App.controller('WordDtoController', ['$scope','$log', '$state', '$window', 'WordDtoService', 'WordSearchObject',
                function($scope, $log, $state, $window, WordDtoService, WordSearchObject) {
    var self = this;
    $scope.$log = $log;

    self.wordDto = {};
    $log.log('WordDtoController');
    self.wordSearch = WordSearchObject.get();
    $log.log( "Search data is " + angular.toJson(WordSearchObject.get()));


    self.fetchAllWords = function(wordSearch){
      WordDtoService.fetchAllWords(wordSearch)
          .then(
               function(d) {
                    self.wordDto = d;
                    $log.log('Data d is ' + angular.toJson(d));

               },
                function(errResponse){
                    console.error('Error while fetching Words');
                }
               );
    };

    self.createWord = function(wordDto){
        WordDtoService.createWord(wordDto)
              .then(
               function(data) {
                      self.wordId = data;
                      $log.log('Id of the word is ' + angular.toJson(data));
                      $window.location.href = '/words/'+self.wordId;

                 },
                  function(errResponse){
                      console.error('Error while fetching Words');
                  }
                 );
    };

    self.fetchAllWords(self.wordSearch);

    self.addMeaning = function() {
        $log.log('After addition ' + angular.toJson($scope.wordDto));
        $scope.wordDto.wordMeanings.push({
            id: null,
            explanation: "",
            language: {"id":6,"lang":"Français"},
            word: null,
            wordTypes:[],
            wordUsages:[]
        });
    };

    self.addWordUsage = function(meaningDto) {
        meaningDto.wordUsages.push({
            name: "",
            studentDtoList: []
        });
    };

    self.hello = function() {
        if(typeof self.wordDto.wordMeanings !== 'undefined' && self.wordDto.wordMeanings.length > 0 ){
             $log.log ('Saving current word' + angular.toJson(self.wordDto));
             self.createWord(self.wordDto);
            }
    }

    self.reset = function(){
      self.wordSearch={content:'',language:''};
      $scope.myForm.$setPristine(); //reset Form
    };

}]);
