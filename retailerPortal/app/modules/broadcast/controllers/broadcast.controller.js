var broadcastApp = angular.module('broadcastApp', ['angular-ui-loader']);
broadcastApp.controller('broadcastController', ['$scope', '$rootScope', '$location', '$http', 'Broadcast', '$window', '$loader', '$timeout', function ($scope, $rootScope, $location, $http, Broadcast, $window, $loader, $timeout) {
	$scope.circleList = [];
	$scope.circle = "";
	$scope.notificationList = [];
	$scope.notificationCircles = [];

	//GET CIRCLE LIST FROM SERVER
	$scope.getCircleList = function () {
		var url = '';
		document.body.style.cursor = 'wait';
		url = appConstants.contextPath + "circle";
		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			document.body.style.cursor = 'default';
			if(data && data != null && data.length > 0) {
				$scope.circles = data;
				$timeout(function () {
					$('.selectpicker').selectpicker('refresh');
				}, 0);
			}
			$scope.getNotificationList();
		}).error(function (data, status, headers, config) {
			document.body.style.cursor = 'default';
			console.log("Error" + data);
		});
	};

	//GET NOTIFICATIONS LIST FROM SERVER
	$scope.getNotificationList = function () {
		$loader.start();
		$scope.notificationList = null;
		var circleId = localStorage.getItem("circle");
		if (localStorage.getItem("loggedInUser") === '1') {
			circleId = 'admin';
		}
		Broadcast.fetchNotification(circleId).then(function (response) {
			$loader.stop();
			$scope.notificationList = response;
			for (var i = 0; i < $scope.notificationList.length; i++) {
				var ids = $scope.notificationList[i].circleId.split(',');
				var CirclesStr = ""
				for (var j = 0; j < ids.length; j++) {
					for (var k = 0; k < $scope.circles.length; k++) {
						if (ids[j] == $scope.circles[k][0]) {
							CirclesStr = $scope.circles[k][1] + ", " + CirclesStr
						}
					}
				}
				CirclesStr = CirclesStr.slice(0, -2);
				$scope.notificationCircles[i] = CirclesStr;
			}
			console.log($scope.notificationCircles);
		}, function (error) {
			$loader.stop();
			console.log("Error" + error);
		});
	}

	//DELETE NOTIFICATION
	$scope.deleteNotification = function (messageID) {
		$loader.start();
		Broadcast.deleteNotification(messageID).then(function (response) {
			if (response == 200) {
				$loader.stop();
				alert("Notification deleted successfully.");
				$scope.getNotificationList();
			} else {
				$loader.stop();
				alert("Not able to detele notifications. Please try again later");
			}
		}, function (error) {
			alert("Notification NOT deleted.");
			console.log("Error" + error);
		});
	}

	//INIT BROADCAST
	initializeBroadcast = function () {
		$('#fromDate').datetimepicker({
			viewMode: 'days',
			format: 'DD/MM/YYYY',
			minDate: moment().millisecond(0).second(0).minute(0).hour(0),
			//maxDate: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 7)
			maxDate: moment(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 7)).millisecond(999).second(59).minute(59).hour(23)
		});

		$('#toDate').datetimepicker({
			viewMode: 'days',
			format: 'DD/MM/YYYY',
			minDate: moment().millisecond(0).second(0).minute(0).hour(0),
			useCurrent: false
		});
		$("#fromDate").on("dp.change", function (e) {
			$('#toDate').data("DateTimePicker").minDate(e.date);
		});
		$("#toDate").on("dp.change", function (e) {
			var weekAfterDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 7);
			if (e.date._d > weekAfterDate) {
				$('#fromDate').data("DateTimePicker").maxDate(weekAfterDate);
			} else {
				$('#fromDate').data("DateTimePicker").maxDate(e.date);
			}
		});
		$scope.circles = [];
		$rootScope.$on('user-info', function (event, data) {
			if (data.roleId === '1') {
				$scope.getCircleList();
			} else {
				$scope.circles = [data.circleName];
			}
		});
		$scope.userName = localStorage.getItem('userName');
		$scope.olmID = localStorage.getItem('olmId');
	}();

	$scope.refreshUI = function () {
		$('.selectpicker').selectpicker('val', '');
		$("#fromDate").find("input").val("");
		$("#toDate").find("input").val("");
		$('#fromDate').data("DateTimePicker").destroy();
		$('#toDate').data("DateTimePicker").destroy();
		$scope.title = "";
		$scope.textBroadcast = "";
		$scope.circleList = [];
		$scope.isWithinCircle = false;
		$scope.retailerFile = null;
		$('#fromDate').datetimepicker({
			viewMode: 'days',
			format: 'DD/MM/YYYY',
			minDate: moment().millisecond(0).second(0).minute(0).hour(0),
			maxDate: moment(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 7)).millisecond(999).second(59).minute(59).hour(23)
		});
		$('#toDate').datetimepicker({
			viewMode: 'days',
			format: 'DD/MM/YYYY',
			minDate: moment().millisecond(0).second(0).minute(0).hour(0),
			useCurrent: false
		});
		$("#fromDate").on("dp.change", function (e) {
			$('#toDate').data("DateTimePicker").minDate(e.date);
		});
		$("#toDate").on("dp.change", function (e) {
			var weekAfterDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 7);
			if (e.date._d > weekAfterDate) {
				$('#fromDate').data("DateTimePicker").maxDate(weekAfterDate);
			} else {
				$('#fromDate').data("DateTimePicker").maxDate(e.date);
			}
		});
	}

	$scope.submitNotification = function () {
		var startDate = $("#fromDate").find("input").val();
		var endDate = $("#toDate").find("input").val();
		if (!$scope.circles && ($scope.circleList === undefined || $scope.circleList === null || $scope.circleList.length === 0)) {
			alert("Please select atleast one circle");
			return;
		}
		if ($scope.title === undefined || $scope.title === null || $scope.title === '') {
			alert("Please enter valid message title");
			return;
		}
		if (startDate === undefined || startDate === null || startDate === '') {
			alert("Please select valid start date");
			return;
		}
		if (endDate === undefined || endDate === null || endDate === '') {
			alert("Please select valid end date");
			return;
		}
		if ($scope.textBroadcast === undefined || $scope.textBroadcast === null || $scope.textBroadcast === '') {
			alert("Please enter valid message");
			return;
		}
		var circleList;
		var file = null;
		if ($scope.admin) {
			circleList = $scope.circleList;
		} else {
			circleList = $scope.circle;
			if ($scope.isWithinCircle) {
				file = $scope.retailerFile;
			}
		}
		var notificationTitle = $scope.title;
		var notificationMessage = $scope.textBroadcast;
		$loader.start();
		if ($scope.admin) {
			Broadcast.broadcastToCircleListAdmin(circleList, startDate, endDate, notificationTitle, notificationMessage).then(function (response) {
				if (response == 200) {
					$loader.stop();
					alert("Notification sent.");
					$scope.getNotificationList();
				} else {
					alert("Not able to send notification. Please try again later");
				}
			}, function (error) {
				alert("Notification NOT sent.");
				console.log("Error" + error);
			});
		} else if (file == null && !$scope.isWithinCircle) {
			Broadcast.broadcastToCircle(circleList, startDate, endDate, notificationTitle, notificationMessage).then(function (response) {
				if (response == 200) {
					$loader.stop();
					alert("Notification sent.");
					$scope.getNotificationList();
				} else {
					alert("Not able to send notification. Please try again later");
				}
			}, function (error) {
				alert("Notification NOT sent.");
			});
		} else {
			Broadcast.broadcastToCustomers(circleList, file, startDate, endDate, notificationTitle, notificationMessage).then(function (response) {
				if (response == 200) {
					$loader.stop();
					alert("Notification sent.");
					$scope.getNotificationList();
				} else {
					alert("Not able to send notification. Please try again later");
				}
			}, function (error) {
				alert("Notification NOT sent.");
			});
		}
		$scope.refreshUI();
	}
}]);