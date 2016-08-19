
(function(){
	
	"use strict";
	
	
	angular.module('ironTrainWeb')
		.config(function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('error', {
			      url: "/error",
			      templateUrl: "app/error/error.html",
			      controller: "ErrorPageController as errPage"
			    });
			});
	
		  
})();
