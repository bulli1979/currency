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
					console.log(from + " " + to);
					var correct = true;
					if(amount == ""){
						$scope.errortext = "Bitte gebe eine Ganzzahlbetrag ein!\n"
							correct = false;
					}
					if(from == null || to == null){
						$scope.errortext += "Bitte fülle Start und Zielwährung aus!\n "
							correct = false;
					}else if(from == to){
						$scope.errortext += "Start und Zielwährung müssen unterschiedlich sein!"
							correct = false;
					}
					return correct;
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
							$scope.result = "Ergebnis " + vm.calculation.value + " " + vm.calculation.from + " sind "  + vm.calculation.result + " " + vm.calculation.to;
						})
					}
				}
			} ]);
})();