var offlineApp = angular.module('offlineApp', ['ngRoute', 'ngCookies', 'ngFileUpload', 'angular-ui-loader']);
offlineApp.controller('offlineCtrl', ['$scope', '$rootScope', '$location', 'Upload', '$timeout', '$http', '$filter', '$window', 'fileLoader', 'uploadPic', '$loader', function ($scope, $rootScope, $location, Upload, $timeout, $http, $filter, $window, fileLoader, uploadPic, $loader) {
	$scope.countShow = false;
	$scope.check2;
	$scope.circles = [];

	$scope.getArchives = function () {
		//File Loader
		$rootScope.check2 = 5;
		document.body.style.cursor = 'wait';
		$loader.start();
		fileLoader.loadFile().then(function (data) {
			$loader.stop();
			$rootScope.offlineList = [];
			$rootScope.schemeTargetList = [];
			$rootScope.schemeList = [];
			$rootScope.navratnaList = [];
			for (var i = 0; i < data.length; i++) {
				var temp_obj = {
					'fileId': data[i].fileId,
					'fileName': data[i].fileName,
					'createdDate': $filter('date')(new Date(data[i].createdDate), 'dd/MM/yyyy'),
					'isValid': data[i].isValidFile
				};
				switch (data[i].fileTypeId) {
					case 3:
						$rootScope.offlineList.push(temp_obj);
						break;
					case 1:
						$rootScope.schemeTargetList.push(temp_obj);
						break;
					case 2:
						$rootScope.schemeList.push(temp_obj);
						break;
					case 4:
						$rootScope.navratnaList.push(temp_obj);
						break;
					default:
						$rootScope.navratnaList.push(temp_obj);
				}
			}
			document.body.style.cursor = 'default';
		}, function (data) {
			$loader.stop();
			document.body.style.cursor = 'default';
			console.log("Error" + data);
		});
	};

	$scope.set_color = function (isValid) {
		if (isValid == 1) {
			return {
				color: "green"
			}
		} else {
			return {
				color: "red"
			}
		}
	};

	// INITIALIZE RETAILER FEATURE PAGES
	var initializeOffline = function () {
		$scope.circles = [];
		$rootScope.$on('user-info', function (event, data) {
			if (data.roleId === '1') {
				$scope.circle();
			} else {
				$scope.circles = [data.circleName];
			}
		});
		/* if ($rootScope.check2 != 5) {
			$scope.fileLoader();
		} */
		$scope.getArchives();
	}();

	//UPLOAD FILE HANDLER
	$scope.UploadFileHandler = function (fileType) {
		var file = $scope.picFile;
		var circleId = $scope.circleNavratna;
		$loader.start();
		if (fileType != 4 && circleId == null) {
			alert("Select the Circle!");
		} else {
			if (file == null) {
				alert('Please select a file of valid type');
				return;
			}
			uploadPic.upload(file, fileType, circleId).then(function (response) {
				$loader.stop();
				file.result = response;
				$scope.picFile = "";
				$scope.getArchives();
			}, function (response) {
				if (response.status > 0)
					$scope.errorMsg = null;
			}, function (evt) {
				file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
				if (file.progress == 100) {
					$window.location.reload();
				}
			});
		}
	}

	$scope.delete1 = function (fileId, fileTypeID) {
		$loader.start();
		document.body.style.cursor = 'wait';
		var url = appConstants.contextPath + "delete/" + fileId + "/" + fileTypeID;
		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			alert("File Deleted Successfully!");
			$scope.getArchives();
		}).error(function (data, status, headers, config) {
			document.body.style.cursor = 'default';
			console.log("Error" + data);
			alert("File Deleted Successfully!");
		});
	};

	$scope.download = function (fileId, fileTypeId, fileName) {
		var url = appConstants.contextPath + "download/" + fileId + "/" + fileTypeId + "/" + fileName;
		window.open(url, "_self");
	};

	$scope.circle = function () {
		$loader.start();
		document.body.style.cursor = 'wait';
		var url = appConstants.contextPath + "circle";
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
		}).error(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			console.log("Error" + data);
		});
	}
	$scope.fetchCircleName = function (circleId) {
		$loader.start();
		document.body.style.cursor = 'wait';
		var url = appConstants.contextPath + "circle/" + circleId;
		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			$loader.stop();
			if (data == "") {
				$scope.signOut();
			}
			document.body.style.cursor = 'default';
			$scope.circles = data;
		}).error(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			console.log("Error" + data);
		});
	};

	$scope.count = function (fileId5) {
		$loader.start();
		$scope.countShow = false;
		document.body.style.cursor = 'wait';
		var url = appConstants.contextPath + "count/" + fileId5;
		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			$loader.stop();
			document.body.style.cursor = 'default';
			if (data == 0) {
				$scope.countMsg = "Please download the file to find errors in the CSV.";
				alert($scope.countMsg);
			} else {
				if (data == 3) {
					$scope.countMsg = "Something went wrong. Please try later.";
					alert($scope.countMsg);
				} else {
					$scope.countMsg = data + "% data has been uploaded.";
					alert($scope.countMsg);
				}
			}
		}).error(function (data, status, headers, config) {
			document.body.style.cursor = 'default';
			console.log(data);
			alert("Something failed!");
		});
	};
}]);