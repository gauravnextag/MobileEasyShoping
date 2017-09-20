try {
	var viewContextPath = "app/modules/"
	var mainApp = angular.module('myApp', ['ngRoute', 'ngMessages', 'login']);
	mainApp.run(function ($rootScope, $location) {
			$rootScope.location = $location;
		})
		.config(function ($routeProvider) {
			$routeProvider
				.when('/', {
					templateUrl: viewContextPath + 'login/views/login.html'
				})
				.when('/retailer', {
					resolve: {
						"check": function ($location, $rootScope) {

						}
					},
					templateUrl: viewContextPath + 'retailer/views/retailer.html'
				})
                .when('/distributor', {
                    resolve: {
                        "check": function ($location, $rootScope) {

                        }
                    },
                    templateUrl: viewContextPath + 'distributor/views/distributor.html'
                })
				.otherwise({
					redirectTo: '/'
				});
		});
} catch (e) {
	console.log(e);
}