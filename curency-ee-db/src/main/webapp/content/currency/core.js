
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router', 'colorpicker.module', 'pascalprecht.translate'])
		.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
			  $urlRouterProvider.otherwise("/home");
			  $translateProvider.useLoader('asyncLoader');
		});
})();




(function() {
	"use strict";

	angular.module('currencyWeb').factory(
			'currencyEndpoint',
			[ '$resource' ,function($resource) {

				var options = { 'query':  { isArray:false} };
				
				var currencyEndpoint = $resource('${rest-base-url}/getall',
						{}, options);
				
				
				return currencyEndpoint;
			}]);
})();
(function() {
	"use strict";

	angular.module('currencyWeb').factory('currencyEndpoint',[ '$resource' , function($resource) {

		var currencyEndpoint = {};
		var options = {
			'query' : {
				isArray : false
			}
		};

	

		return currencyEndpoint;
	}]);

})();



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

