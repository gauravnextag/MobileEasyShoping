mainApp.controller('navCtrl', ['$scope', '$rootScope', '$location', '$timeout', '$http', '$window', 'navService', function ($scope, $rootScope, $location, $timeout, $http, $window, navService) {
	// INITIALIZE NAVIGATION
	$scope.initializeNavigation = function () {
		$rootScope.$on('selected-page', function (event, data) {
			$scope.page = (data != undefined && data != null) ? data : '';
		});
		$rootScope.$on('user-info', function (event, data) {
			// console.log(data);
			if (data.roleId === '1') {
				$scope.admin = true;
			} else {
				$scope.admin = false;
			}
			$scope.userName = data.userName;
			$scope.olmId = data.olmId;
		});
	}();

	$scope.signOut = function () {
		var data = {
			olmId: $scope.olmId
		}
		navService.logout(data).then(function (response) {
			if (response) {
				alert("logged out Successfully");
				$location.path('/');
				localStorage.clear();
				delete $rootScope.circles;
			}
		}, function (error) {
			alert("Some error occured. Please try login again!");
		});
	}
	$scope.offlineHandler = function () {
		$scope.page = 'Offline Comission';
		$location.path('/offline');
	}
	$scope.archivesHandler = function () {
		$scope.page = 'Archives';
		$location.path('/archives');
	}
	$scope.manageHandler = function () {
		$scope.page = 'Manage Users';
		$location.path('/manage');
	}
	$scope.navratnabaseHandler = function () {
		$scope.page = 'Navratna Base';
		$location.path('/navratnabase');
	}
	$scope.schemeHandler = function () {
		$scope.page = 'Navratna Scheme Details';
		$location.path('/scheme');
	}
	$scope.schemetgtHandler = function () {
		$scope.page = 'Navratna Scheme Tgt/Ach';
		$location.path('/schemetgt');
	}
	$scope.broadcastHandler = function () {
		$scope.page = 'Navratna Scheme Tgt/Ach';
		$location.path('/broadcast');
	}
}]);