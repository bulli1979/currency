
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router'])
		.config(function($stateProvider, $urlRouterProvider) {
			  $urlRouterProvider.otherwise("/home");
		});
})();




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

(function(){
	
	"use strict";
	
	angular.module('currencyWeb')
		.controller('HomeController', ['CurrencyEndpoint','$scope', function($scope, CurrencyEndpoint) {
			var vm = this;
			
			var queryAll = CurrencyEndpoint.getAll(function(){
				vm.currencys = queryAll || [];
			});
		
			console.log(currencys);
			
			$scope.data = {
					model: null,
					availableOptions: [
				      {id: '1', name: 'Option A'},
				      {id: '2', name: 'Option B'},
				      {id: '3', name: 'Option C'}
		    ]
		   };
		}]);
})();

(function(){
	
	"use strict";
	
	
	angular.module('currencyWeb')
		.config([ '$stateProvider', '$urlRouterProvider' , function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('home', {
			      url: "/home",
			      templateUrl: "content/ui/Home/home.html",
			    });
			}]);
	
		  
})();

