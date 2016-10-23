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