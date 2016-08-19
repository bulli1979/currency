(function() {
	"use strict";

	angular.module('ironTrainWeb').factory('asyncLoader', function ($q, $http) {
		 
		  return function (options) {
		    var deferred = $q.defer(),
		        translations;
		    
		    var lang = options.key;
		    var translations; 
		    var data = $http.get("${rest-base-url}/label/getlanguagevalues").then(function(response){
		    	for (var i = 0; i < response.data.length; i++) {
			   		if(lang === response.data[i].lang) {
			   			translations = response.data[i].values;
			   			deferred.resolve(translations);
			   			break;
			   		}  
			   	}
		    });
		    
		    return deferred.promise;
		  };
		});	
})();

