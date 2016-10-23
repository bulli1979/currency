(function() {
	"use strict";

	angular.module('currencyWeb').factory(
			'CurrencyEndpoint',
			[ '$resource' , function($resource) {
				var CurrencyEndpoint = {};
				var options = { 'query':  { isArray:false} };
				console.log("start: ${rest-base-url}");
				
				var allPath = $resource('${rest-base-url}/getAll', {}, options);
				console.log("problem here");
				
				
								
				CurrencyEndpoint.getAll = function(succ, err) {
					console.log("here");
					return allPath.get;
				};
				
				return CurrencyEndpoint;
			}]);
})();
