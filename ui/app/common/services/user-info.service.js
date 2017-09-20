mainApp.service('userInfoService', function ($http, $q) {
	/* USER INFORMATION SERVICE */
	this.getUserInfo = function (data) {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "fetchUserInfo";
		$http({
			method: 'GET',
			url: url,
			data: data,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			deferred.resolve(data);
		}).error(function (data, status, headers, config) {
			deferred.reject(data);
		});
		return deferred.promise;
	}
});