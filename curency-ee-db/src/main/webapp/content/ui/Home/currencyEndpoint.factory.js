(function() {
	"use strict";

	angular
			.module('currencyWeb')
			.factory(
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

								var allPath = $resource(
										'http://localhost:8080/currency-ee-db/service/api/getall', {}, {});
								var calculatePath = $resource(
										'http://localhost:8080/currency-ee-db/service/api/change/:amount/:from/:to/',
										{
											amount : "@amount",
											from : "@from",
											to : "@to"
										}, options);

								CurrencyEndpoint.getAll = function() {
									return allPath.query({});
								};

								CurrencyEndpoint.calculate = function(amount, from, to, succ, err) {
									return calculatePath.get({
										"amount" : amount,
										"from" : from,
										"to" : to
									},succ,err);
								}

<<<<<<< HEAD
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
=======
								return CurrencyEndpoint;
							} ]);
>>>>>>> 842d7eb5e0a48b53ccb5b2599d1aa178bdc973bb
})();
