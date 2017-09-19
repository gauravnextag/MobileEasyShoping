mainApp.service('navService', function ($http, $q) {
	/* LOGOUT SERVICE */
	this.logout = function (data) {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "logout";
		$http({
			method: 'POST',
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