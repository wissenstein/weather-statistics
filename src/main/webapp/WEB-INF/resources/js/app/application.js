(function (angular, appConfig) {

    // Adds a trailing zero for one-digit numbers
    function padTo2(number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return "" + number;
        }
    }

    function formatDate(date) {
        return date.year + "-"
                + padTo2(date.month) + "-" + padTo2(date.day);
    }

    angular.module("weatherApp", [])
    .constant("CTX_PATH", appConfig.contextPath)
    .config(function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("WeatherByDateController", [
        "$scope",
        "$http",
        "CTX_PATH",
        function (
                $scope,
                $http,
                CTX_PATH) {

            $scope.day = null;

            $scope.loadWeatherByDate = function () {
                $http.get(CTX_PATH
                        + "/weather-from-service/" + $scope.request.date)
                .success(function (data) {
                    $scope.day = data;
                    $scope.day.dateString = formatDate($scope.day.date);
                })
                .error(function (error) {
                    $scope.error = error;
                });
            };
        }
    ])
    .controller("WeatherByPeriodController", [
        "$scope",
        "$http",
        "CTX_PATH",
        function (
                $scope,
                $http,
                CTX_PATH) {

            $scope.request = {};

            $scope.loadWeatherByPeriod = function () {
                $http.get(CTX_PATH
                        + "/weather-from-service/from/"
                        + $scope.request.firstDate
                        + "/to/" + $scope.request.lastDate)
                .success(function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var day = data[i];
                        day.dateString = formatDate(day.date);
                    }

                    $scope.period = data;
                })
                .error(function (error) {
                });
            };
        }
    ]);
})(window.angular, window.appConfig);
