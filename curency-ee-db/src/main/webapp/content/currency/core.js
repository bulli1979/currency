
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router'])
		.config(function($stateProvider, $urlRouterProvider) {
			  $urlRouterProvider.otherwise("/home");
		});
})();




(function() {
	"use strict";

	angular
			.module('currencyWeb')
			.factory(
					'CurrencyEndpoint',
					[
							'$resource',
							function($resource) {
								var CurrencyEndpoint = {};

								var options = {
									'query' : {
										isArray : false
									}
								};

								var allPath = $resource(
										'http://localhost:8080/currency-ee-db/service/api/getall', {}, {});
								var calculatePath = $resource(
										'http://localhost:8080/currency-ee-db/service/api/change/:amount/:from/:to/',
										{
											amount : "@amount",
											from : "@from",
											to : "@to"
										}, options);

								CurrencyEndpoint.getAll = function() {
									return allPath.query({});
								};

								CurrencyEndpoint.calculate = function(amount, from, to, succ, err) {
									return calculatePath.get({
										"amount" : amount,
										"from" : from,
										"to" : to
									},succ,err);
								}

								return CurrencyEndpoint;
							} ]);
})();

(function() {

	"use strict";

	angular.module('currencyWeb').controller('HomeController',
			[ '$scope', 'CurrencyEndpoint', function($scope, CurrencyEndpoint) {
				var vm = this;

				vm.currencies = CurrencyEndpoint.getAll();
				
				
				$scope.dataFrom = {
					model : null,
					
					availableOptions : vm.currencies
				};
				
				$scope.dataTo = {
						model : null,
						availableOptions : vm.currencies
				};

				$scope.amount = "";
				
				$scope.result = "";
				
				$scope.calculate = function() {					
					vm.calculation = CurrencyEndpoint.calculate($scope.amount, $scope.dataFrom.model, $scope.dataTo.model, function(){
						$scope.result = "Ergebnis " + vm.calculation.value + " " + vm.calculation.from + " sind "  + vm.calculation.result + " " + vm.calculation.to;
					})
				}
			} ]);
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

