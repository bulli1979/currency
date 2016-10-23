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