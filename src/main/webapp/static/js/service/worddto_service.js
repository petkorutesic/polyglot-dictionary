'use strict';

App.factory('WordDtoService', ['$http', '$q', function($http, $q){

	return {

			fetchAllWords: function(wordSearch) {
					return $http.post('http://localhost:8080/sparql/wordsearch',wordSearch)
							.then(
									function(response){
										return response.data;
									},
									function(errResponse){
										console.error('Error while fetching words');
										return $q.reject(errResponse);
									}
							);
			},
		    
			createWord: function(wordDto){
					return $http.post('http://localhost:8080/sparql/wordsave', wordDto)
							.then(
									function(response){
										return response.data;
									},
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
