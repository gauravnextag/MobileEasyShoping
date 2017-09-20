offlineApp.service('uploadPic', function ($timeout, $window, $http, $q) {
	this.upload = function (file, fileType, circleId) {
		var reqObj = null,
			circle = 1;
		if (fileType != 4) {
			circle = circleId;
		}
		reqObj = {
			'circleId': circle,
			'userId': localStorage.getItem('userId'),
			'file': file,
			'fileType': fileType
		};

		var form = new FormData();
		form.append("circleId", reqObj.circleId);
		form.append("userId", reqObj.userId);
		form.append("file", reqObj.file);
		form.append("fileType", reqObj.fileType);
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