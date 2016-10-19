(function() {
	"use strict";

	angular.module('currencyWeb').factory(
			'currencyEndpoint',
			[ '$resource' ,function($resource) {

				var options = { 'query':  { isArray:false} };
				
				var currencyEndpoint = $resource('${rest-base-url}/getall',
						{}, options);
				
				
				return currencyEndpoint;
			}]);
})();