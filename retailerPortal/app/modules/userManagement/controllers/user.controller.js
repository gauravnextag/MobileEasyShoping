var userManageApp = angular.module('userManageApp', ['ngRoute', 'ngCookies', 'ngFileUpload', 'angular-ui-loader']);
userManageApp.controller('userManageCtrl', ['$scope', '$rootScope', '$location', '$timeout', '$http', '$filter', '$window', 'manageUserService', '$loader', function ($scope, $rootScope, $location, $timeout, $http, $filter, $window, manageUserService, $loader) {
	$scope.circles = [];

	//GET CIRCLE LIST FROM SERVER
	$scope.getCircleList = function () {
		var url = '';
		document.body.style.cursor = 'wait';
		$loader.start();
		url = appConstants.contextPath + "circle";
		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			$scope.circles = data;
			$('.selectpicker').selectpicker('render');
		}).error(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			console.log("Error" + data);
		});
	};

	//INIT USER MANAGEMENT
	initializeUserManagement = function () {
		$scope.circles = [];
		$rootScope.$on('user-info', function (event, data) {
			if (data.roleId === '1') {
				$scope.getCircleList();
			} else {
				$scope.circles = [data.circleName];
			}
		});
	}();

	$scope.isValidMobile = function () {
		var pattern = new RegExp("^[0-9]{10}$");
		return pattern.test($scope.mobileUser);
	};

	$scope.addUser = function () {
		if ($scope.nameUser == undefined || $scope.nameUser == "" || $scope.nameUser == null) {
			alert("Please enter valid User name");
			return;
		}
		if ($scope.olmId == undefined || $scope.olmId == null || $scope.olmId == "" || !$scope.isValidOlmid($scope.olmId)) {
			alert("Please enter valid olmId");
			return;
		}
		if ($scope.circleUser == undefined || $scope.circleUser == null || $scope.circleUser == "") {
			alert("Please select circle");
			return;
		}
		if ($scope.roleUser == undefined || $scope.roleUser == null || $scope.roleUser == "") {
			alert("Please select user role");
			return;
		}
		if ($scope.emailUser == undefined || $scope.emailUser == null || $scope.emailUser == "") {
			alert("Please enter valid email id");
			return;
		}
		if ($scope.mobileUser == null || $scope.mobileUser == null || $scope.mobileUser == null || !$scope.isValidMobile()) {
			alert("Please enter 10 digits valid mobile number");
			return;
		}
		$loader.start();
		document.body.style.cursor = 'wait';
		var data = {
			'name': $scope.nameUser,
			'olm': $scope.olmId,
			'role': $scope.roleUser,
			'circle': $scope.circleUser,
			'email': $scope.emailUser,
			'mobile': $scope.mobileUser
		}
		manageUserService.userAdd(data).then(function (result) {
			$loader.stop();
			document.body.style.cursor = 'default';
			if (result && result == 1) {
				alert("User Added Successfully!");
			} else {
				alert("User Already Exists!");
			}

			$scope.nameUser = "";
			$scope.olmId = "";
			$scope.roleUser = "";
			$scope.circleUser = "";
			$scope.emailUser = "";
			$scope.mobileUser = "";
		}, function (error) {
			document.body.style.cursor = 'default';
			alert("Something went wrong! Please try again later");
			$window.location.reload();
		});
	}

	$scope.deleteUser = function () {
		if ($scope.deleteOlmId == undefined || $scope.deleteOlmId == null || $scope.deleteOlmId == "" || !$scope.isValidOlmid($scope.deleteOlmId)) {
			alert("Please enter valid olmId");
			return;
		}
		document.body.style.cursor = 'wait';
		$loader.start();
		if ($scope.deleteOlmId != null) {
			manageUserService.userDelete($scope.deleteOlmId).then(function (result) {
				$scope.deleteOlmId = "";
				$loader.stop();
				document.body.style.cursor = 'default';
				if (result == 1) {
					alert("User Deleted Successfully!");
				} else {
					alert("User Doesn't Exist!")
				}
			}, function (error) {
				document.body.style.cursor = 'default';
				alert("Something failed!")
			})
		} else {
			alert("Please enter valid olmid");
		}
	}
}]);