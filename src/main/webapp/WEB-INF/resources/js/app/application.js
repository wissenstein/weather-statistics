(function (angular, appConfig) {
    angular.module("weatherApp", [])
    .constant("CTX_PATH", appConfig.contextPath)
    .config(function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("SaveDayController", [
        "$scope",
        "$http",
        "CTX_PATH",
        function (
                $scope,
                $http,
                CTX_PATH) {

            $scope.day = {date:null};
            $scope.tmp = {};

            $scope.saveDay = function () {
                var dateString = $scope.tmp.dateString;
                $scope.day.date = {
                    year: Number(dateString.substring(0, 4)),
                    month: Number(dateString.substring(5, 7)),
                    day: Number(dateString.substring(8, 10))
                };
                $http.post(
                        CTX_PATH + "/admin/save-day",
                        $scope.day)
                .success(function (data) {
                    $scope.message = "success! " + data;
                })
                .error(function (error) {
                    $scope.message = "error! " + error;
                });
            };
        }
    ])
    .controller("WeatherByDateController", [
        "$scope",
        "$http",
        "CTX_PATH",
        function (
                $scope,
                $http,
                CTX_PATH) {

            $scope.day = null;

            // Adds a trailing zero for one-digit numbers
            function padTo2(number) {
                if (number < 10) {
                    return "0" + number;
                } else {
                    return "" + number;
                }
            }

            $scope.loadWeatherByDate = function () {
                $http.get(CTX_PATH
                        + "/weather-from-service/" + $scope.request.date)
                .success(function (data) {
                    $scope.day = data;
                    var date = $scope.day.date;
                    $scope.day.dateString = date.year + "-"
                            + padTo2(date.month) + "-" + padTo2(date.day);
                })
                .error(function (error) {
                    $scope.error = error;
                });
            };
        }
    ]);
})(window.angular, window.appConfig);
