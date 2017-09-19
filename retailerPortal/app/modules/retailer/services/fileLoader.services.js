offlineApp.service('fileLoader', function ($http, $q) {
	this.loadFile = function () {
		var deferred = $q.defer();
		var url = appConstants.contextPath + "getfile";
		if (localStorage.getItem('loggedInUser') == 2) {
			url = appConstants.contextPath + "getfile/" + localStorage.getItem('circle');
		}

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
	}
});