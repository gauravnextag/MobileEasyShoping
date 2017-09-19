loginApp.service('loginService', function ($http, $q) {
	/* LOGIN SERVICE */
	this.login = function (data) {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "login";
		$http({
			method: 'POST',
			url: url,
			data: data
		}).success(function (data, status, headers, config) {
			deferred.resolve(data);
		}).error(function (data, status, headers, config) {
			deferred.reject(data);
		});
		return deferred.promise;
	};
});