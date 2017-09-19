broadcastApp.service('Broadcast', function ($http, $q) {
    //BROADCAST TO WHOLE CIRCLE(S) - ADMIN
    this.broadcastToCircleListAdmin = function (circleList, startDate, endDate, notificationTitle, notificationMessage) {
        var reqObj = null;
        var url = broadcastContext + 'publishAdminCircle';
        var form = new FormData();
        var deferred = $q.defer();
        reqObj = {
            'circleId': 'admin',
            'notificationTitle': notificationTitle,
            'notificationMessage': notificationMessage,
            'startDate': startDate,
            'endDate': endDate
        };

        $http({
            method: 'POST',
            url: url,
            //data: circleList,
            headers: {
                'Accept': '*/*',
                'msgInfo': JSON.stringify(reqObj),
                'circleList': JSON.stringify(circleList),
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
                'Authorization': localStorage.getItem('token')
            },
        }).success(function (result) {
            deferred.resolve(result);
        }).error(function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    //BROADCAST TO WHOLE CIRCLE
    this.broadcastToCircle = function (circle, startDate, endDate, notificationTitle, notificationMessage) {
        var reqObj = null;
        var url = broadcastContext + 'publishCircle';
        var deferred = $q.defer();
        var form = new FormData();
        reqObj = {
            'circleId': circle,
            'notificationTitle': notificationTitle,
            'notificationMessage': notificationMessage,
            'startDate': startDate,
            'endDate': endDate
        };

        $http({
            method: 'POST',
            url: url,
            headers: {
                'Authorization': localStorage.getItem('token'),
                'msgInfo': JSON.stringify(reqObj),
            },
        }).success(function (result) {
            deferred.resolve(result);
        }).error(function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    //BROADCAST TO RETAILERS WITHIN SELECTED CIRCLE
    this.broadcastToCustomers = function (circle, file, startDate, endDate, notificationTitle, notificationMessage) {
        var reqObj = null;
        var url = broadcastContext + 'publishCustomers';
        var form = new FormData();
        var deferred = $q.defer();
        reqObj = {
            'circleId': circle,
            'notificationTitle': notificationTitle,
            'notificationMessage': notificationMessage,
            'startDate': startDate,
            'endDate': endDate
        };

        var form = new FormData();
        form.append("file", file);

        $http({
            method: 'POST',
            url: url,
            data: form,
            headers: {
                'msgInfo': JSON.stringify(reqObj),
                'Content-Type': undefined,
                'Accept': '*/*',
                'Authorization': localStorage.getItem('token')
            },
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    //DELETE PARTICULAR NOTIFICATION
    this.deleteNotification = function (messageID) {
        var deferred = $q.defer();
        var url = broadcastContext + 'deleteNotification';
        $http({
            method: 'POST',
            url: url,
            headers: {
                'Accept': '*/*',
                'messageID': messageID,
                'Authorization': localStorage.getItem('token')
            }
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (error) {
            deferred.reject(error);
        });
        return deferred.promise;

    };

    //FETCH ALL NOTIFICATIONS
    this.fetchNotification = function (circleID, admin) {
        var url = broadcastContext + 'fetchNotifications';
        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: url,
            headers: {
                'circleId': circleID,
                'Authorization': localStorage.getItem('token')
            }
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };
})