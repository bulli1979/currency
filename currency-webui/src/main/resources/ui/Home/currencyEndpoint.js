(function() {
	"use strict";

	angular.module('currencyWeb').factory('currencyEndpoint',[ '$resource' , function($resource) {

		var currencyEndpoint = {};
		var options = {
			'query' : {
				isArray : false
			}
		};

	

		return currencyEndpoint;
	}]);

})();

