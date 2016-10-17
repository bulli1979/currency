
(function(){
	
	"use strict";
	
	
	angular.module('currencyWeb')
		.config(function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('error', {
			      url: "/error",
			      templateUrl: "ui/error/error.html",
			      controller: "ErrorPageController as errPage"
			    });
			});
	
		  
})();
