try {
	var loginApp = angular.module('loginApp', ['ngRoute', 'ngCookies', 'angular-ui-loader']);
	loginApp.controller('loginCtrl', function ($location, $scope, $rootScope, $http, EncryptPass, loginService, $loader) {
		// INITIALIZE LOGIN
		$scope.initializeLogin = function() {
			$rootScope.page = "LOGIN";
		}();
		$scope.loginHandler = function () {
			if ($scope.inputOlmId == null || $scope.password == null) {
				alert("Please fill the credentials!")
			} else {
				var encodedPass = EncryptPass.encode($scope.password, "123");
				var data = {
					"olm": $scope.inputOlmId.toUpperCase(),
					"password": encodedPass
				};
				document.body.style.cursor = 'wait';
				$scope.disabled = true;
				$loader.start();
				loginService.login(data).then(function (data) {
					$loader.stop();
					$scope.disabled = false;
					document.body.style.cursor = 'default';
					if (data.roleId == 1) {
						$rootScope.admin = true;
						localStorage.setItem('token', data.token);
						$location.path('/offline');
					} else {
						if (data.roleId == 2) {
							$rootScope.admin = false;
							localStorage.setItem('token', data.token);
							$location.path('/offline');
						} else {
							if (data.status == 999) {
								alert("User Authentication Failed!");
							}
							else {
								alert("User not found. Please contact the administrator.");
							}
						}
					}
				}, function (error) {
					$loader.stop();
					$scope.disabled = false;
					document.body.style.cursor = 'default';
					console.log(error + status);
					alert("Login Failed! Please try again later.");
				});
			}
		}
	});
} catch (e) {
	console.log(e);
}