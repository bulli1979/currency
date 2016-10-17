(function(){
	
	"use strict";
	
	angular.module('currencyWeb')
		.controller('ErrorPageController', function(AjaxErrorHandler) {
			
			var vm = this;
			vm.code = AjaxErrorHandler.getErrorCode();
			vm.message = AjaxErrorHandler.getErrorMessage();
			
		});
})();