
(function(){
	
	"use strict";
	
	var smarterApp = angular.module('currencyWeb', ['ngResource', 'ui.router', 'colorpicker.module', 'pascalprecht.translate'])
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

	angular.module('ironTrainWeb').factory(
			'Endpoint',
			function($resource) {

				
			})
})();

(function() {
	"use strict";

	angular.module('ironTrainWeb').factory('asyncLoader', function ($q, $http) {
		 
		  return function (options) {
		    var deferred = $q.defer(),
		        translations;
		    
		    var lang = options.key;
		    var translations; 
		    var data = $http.get("${rest-base-url}/label/getlanguagevalues").then(function(response){
		    	for (var i = 0; i < response.data.length; i++) {
			   		if(lang === response.data[i].lang) {
			   			translations = response.data[i].values;
			   			deferred.resolve(translations);
			   			break;
			   		}  
			   	}
		    });
		    
		    return deferred.promise;
		  };
		});	
})();


(function() {
	"use strict";

	angular.module('ironTrainWeb').factory('AjaxErrorHandler', function($state) {
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
	
	angular.module('ironTrainWeb')
		.controller('ErrorPageController', function(AjaxErrorHandler) {
			
			var vm = this;
			vm.code = AjaxErrorHandler.getErrorCode();
			vm.message = AjaxErrorHandler.getErrorMessage();
			
			
			
			
			
			
			
			
		});
})();

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

