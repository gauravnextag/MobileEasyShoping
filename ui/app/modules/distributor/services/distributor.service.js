shopping.service('distributor', function ($timeout, $window, $http, $q) {
	this.upload = function (Id) {
				var deferred = $q.defer();
		$http({
			method: 'POST',
			url: appConstants.contextPath + 'upload/file',
			data: form,
			headers: {
				'Content-Type': undefined,
				'Accept': '*/*',
				'Authorization': localStorage.getItem('token')
			},
			responseType: 'arraybuffer',
			cache: true
		}).then(function (result) {
			var uploadObj = null;
			if (result.data != undefined && result.data != null) {
				uploadObj = result.data;
			}
			deferred.resolve(uploadObj);
		}, function (error) {
			deferred.reject(error);
		});
		return deferred.promise;
	}
});