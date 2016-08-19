(function(){
	
	"use strict";
	
	angular.module('ironTrainWeb')
		.controller('ErrorPageController', function(AjaxErrorHandler) {
			
			var vm = this;
			vm.code = AjaxErrorHandler.getErrorCode();
			vm.message = AjaxErrorHandler.getErrorMessage();
			
			
			
			
			
			
			
			
		});
})();