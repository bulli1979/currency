
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router', 'colorpicker.module', 'pascalprecht.translate'])
		.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
			  
			  // SBA: maybe the routing concept has to be reworked
			  // would be better to route the whole page content and work with
				// nested views
			  // For any unmatched url, redirect to /applist
				
			  $urlRouterProvider.otherwise("/home");
			  $translateProvider.useLoader('asyncLoader');
		});
})();



