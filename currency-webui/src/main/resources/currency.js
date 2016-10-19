
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router', 'colorpicker.module', 'pascalprecht.translate'])
		.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
			  $urlRouterProvider.otherwise("/home");
			  $translateProvider.useLoader('asyncLoader');
		});
})();



