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
