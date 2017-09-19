try {
	var viewContextPath = "app/modules/"
	var mainApp = angular.module('myApp', ['ngRoute', 'ngCookies', 'ngFileUpload', 'ngMessages', 'loginApp', 'offlineApp', 'broadcastApp', 'userManageApp']);
	mainApp.run(function ($rootScope, $location) {
			$rootScope.location = $location;
		})
		.config(function ($routeProvider) {
			$routeProvider
				.when('/', {
					templateUrl: viewContextPath + 'login/views/login.html'
				})
				.when('/offline', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Offline Comission');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'retailer/views/offlineCommission.html'
				})
				.when('/archives', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Archives');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'retailer/views/archives.html'
				})
				.when('/manage', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Manage Users');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'userManagement/views/manage.html'
				})
				.when('/navratnabase', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Navratna Base');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'retailer/views/navratnabase.html'
				})
				.when('/scheme', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Navratna Scheme Details');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'retailer/views/navratnaschemedetails.html'
				})
				.when('/schemetgt', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Navratna Scheme Tgt/Ach');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'retailer/views/navratnaschemetgt.html'
				})
				.when('/broadcast', {
					resolve: {
						"check": function ($location, $rootScope, userInfoService) {
							if (localStorage.getItem('token') !== undefined &&
								localStorage.getItem('token') !== null &&
								localStorage.getItem('token') !== '') {
								userInfoService.getUserInfo().then(function (response) {
									if (response.result && response.result !== null) {
										if (response.result.roleId === '1') {
											$rootScope.admin = true;
										} else {
											$rootScope.admin = false;
										}
										$rootScope.userName = response.result.userName;
										$rootScope.olmId = response.result.olmId;
										$rootScope.circleId = response.result.circleId;
										$rootScope.circle = response.result.circleName;
										$rootScope.$broadcast('user-info', response.result);
										$rootScope.$broadcast('selected-page', 'Broadcast');
									} else {
										$location.path('/');
									}
								}).catch(function (error) {
									console.log('Error in Get User info' + error);
								});
							} else {
								$location.path('/');
							}
						}
					},
					templateUrl: viewContextPath + 'broadcast/views/broadcast.html'
				})
				.otherwise({
					redirectTo: '/'
				});
		});
} catch (e) {
	console.log(e);
}