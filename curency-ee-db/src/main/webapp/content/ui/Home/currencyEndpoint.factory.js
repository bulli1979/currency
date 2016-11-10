(function() {
	"use strict";

	angular.module('currencyWeb').factory(
			'CurrencyEndpoint',
			[
					'$resource',
					function($resource) {
						var CurrencyEndpoint = {};

						var options = {
							'query' : {
								isArray : false
							}
						};

						var allPath = $resource('/currency/service/api/getall', {},
								{});

						var calculatePath = $resource(
								'/currency/service/api/change/:amount/:from/:to/', {
									amount : "@amount",
									from : "@from",
									to : "@to"
								}, options);

						CurrencyEndpoint.getAll = function() {
							return allPath.query({});
						};

						CurrencyEndpoint.calculate = function(amount, from, to,
								succ, err) {
							return calculatePath.get({
								"amount" : amount,
								"from" : from,
								"to" : to
							}, succ, err);
						}

						return CurrencyEndpoint;
					} ]);
})();
