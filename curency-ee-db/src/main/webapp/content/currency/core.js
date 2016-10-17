
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router', 'colorpicker.module', 'pascalprecht.translate'])
		.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
			  
			  // SBA: maybe the routing concept has to be reworked
			  // would be better to route the whole page content and work with
				// nested views
			  // For any unmatched url, redirect to /applist
			  var loc = location.href;
				
			  $urlRouterProvider.otherwise("/home");
			  $translateProvider.preferredLanguage('de_CH');
			  $translateProvider.preferredLanguage('en_UK');
			  $translateProvider.useLoader('asyncLoader');
		});
})();





(function(){
	
	"use strict";
	
	
	angular.module('currencyWeb')
		.config(function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('home', {
			      url: "/home",
			      templateUrl: "ui/Home/home.html",
			    });
			});
	
		  
})();

(function() {
	"use strict";

	angular.module('currencyWeb').factory('AjaxErrorHandler', function($state) {
		var errCode = "";
		var errMsg = "";
		var errData = "";
		
		var AjaxErrorHandler = {};
		
		AjaxErrorHandler.onError = function(err){
			//TODO: Maybe attempt to send report to server?
			$state.go("error");
			errCode = err.status;
			errMsg = err.statusText;
			errData = err.data;
		}
		
		AjaxErrorHandler.onErrorSilent = function(err){
			//TODO: Dont know if we need to do anything at all here
			console.log("silent");
			console.log(err);
		}
		
		AjaxErrorHandler.getErrorCode = function(){
			return errCode;
		}
		
		AjaxErrorHandler.getErrorMessage = function(){
			return errMsg;
		}
		
		AjaxErrorHandler.getErrorData = function(){
			return errData;
		}
		
		return AjaxErrorHandler;
	});	
})();

(function(){
	
	"use strict";
	
	angular.module('currencyWeb')
		.controller('ErrorPageController', function(AjaxErrorHandler) {
			
			var vm = this;
			vm.code = AjaxErrorHandler.getErrorCode();
			vm.message = AjaxErrorHandler.getErrorMessage();
			
		});
})();

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

