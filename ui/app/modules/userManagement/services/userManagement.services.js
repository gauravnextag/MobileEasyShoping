userManageApp.service('manageUserService', function ($http, $q) {
	this.userAdd = function (data) {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "add";

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
	};

	this.userDelete = function (olmid) {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "delete/user/" + olmid;

		$http({
			method: 'GET',
			url: url,
			headers: {
				'Authorization': localStorage.getItem('token')
			}
		}).success(function (data, status, headers, config) {
			deferred.resolve(data);
		}).error(function (data, status, headers, config) {
			deferred.reject(data);
		});

		return deferred.promise;
	};
})