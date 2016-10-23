
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router'])
		.config(function($stateProvider, $urlRouterProvider) {
			  $urlRouterProvider.otherwise("/home");
		});
})();



