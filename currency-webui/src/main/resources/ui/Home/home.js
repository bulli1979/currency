
(function(){
	
	"use strict";
	
	
	angular.module('currencyWeb')
		.config(function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('home', {
			      url: "/home",
			      templateUrl: "content/ui/Home/home.html",
			    });
			});
	
		  
})();
