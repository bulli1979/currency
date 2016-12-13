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
					if(from == null || from == "" || to == null || to == ""){
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