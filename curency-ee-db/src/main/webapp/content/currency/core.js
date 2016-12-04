
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
			[
					'$resource',
					function($resource) {
						var CurrencyEndpoint = {};

						var options = {
							'query' : {
								isArray : false
							}
						};

						var allPath = $resource('/currency/service/api/getall', {},
								{});

						var calculatePath = $resource(
								'/currency/service/api/change/:amount/:from/:to/', {
									amount : "@amount",
									from : "@from",
									to : "@to"
								}, options);

						CurrencyEndpoint.getAll = function() {
							return allPath.query({});
						};

						CurrencyEndpoint.calculate = function(amount, from, to,
								succ, err) {
							
							return calculatePath.get({
								"amount" : amount,
								"from" : from,
								"to" : to
							}, succ, err);
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
				
				vm.validate = function(){
					var amount = $scope.amount;
					var from = $scope.dataFrom.model;
					var to = $scope.dataTo.model;
					var correct = true;
					var tempErrorText = "";
					var match = /^[0-9]{1,8}/g;
					if(!amount.match(match)){
						tempErrorText = "Bitte gebe einen Ganzzahlbetrag ein! Maximal 8 Zeichen!\n";
							correct = false;
					}
					if(from == null || to == null){
						tempErrorText += "Bitte fülle die Start und Zielwährung aus!\n";
							correct = false;
					}else if(from == to){
						tempErrorText += "Start und Zielwährung müssen unterschiedlich sein!\n";
							correct = false;
					}
					$scope.errortext = tempErrorText;
					return correct;
				}
				
				vm.getLangText = function(short){
					var name = "";
					vm.currencies.forEach(function(currency,index,arr) {
					    if(currency.shortName == short){
					    	name = currency.name;
					    }
					});
					return name
				}
				
				$scope.dataFrom = {
					model : null,
					
					availableOptions : vm.currencies
				};
				
				$scope.dataTo = {
						model : null,
						availableOptions : vm.currencies
				};

				$scope.amount = "";
				$scope.errortext = "";
				$scope.result = "";
				
				$scope.calculate = function() {					
					if(vm.validate()){
						$scope.errortext = "";
						vm.calculation = CurrencyEndpoint.calculate($scope.amount, $scope.dataFrom.model, $scope.dataTo.model, function(){
							$scope.result = "Ergebnis " + vm.calculation.value + " " + vm.getLangText(vm.calculation.from) + " sind "  
							+ vm.calculation.result + " " + vm.getLangText(vm.calculation.to);
						})
					}
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

