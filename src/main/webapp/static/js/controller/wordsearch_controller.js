'use strict';
/*
Simple controller just passing values of search object to next controller
*/
App.controller('WordSearchController', ['$scope', '$log', 'WordSearchObject',
                '$state', function($scope, $log, WordSearchObject, $state) {
          var self = this;
          $scope.$log = $log;
          self.wordSearch={content:'',language:{ id: 4, lang : 'Deutsch'}};


           self.submit = function() {
             // debug;
              if(self.wordSearch.content!=''){
                  console.log('Search for word', self.wordSearch);
                   WordSearchObject.set(self.wordSearch);
                  $state.go('wordDTO');
              }
          };
          
          self.reset = function(){
              self.wordSearch={content:'',language:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
