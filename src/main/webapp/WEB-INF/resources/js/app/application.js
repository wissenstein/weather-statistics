(function (angular, appConfig) {
    angular.module("weatherApp", [])
    .constant("CTX_PATH", appConfig.contextPath)
    .controller("SaveDayController", [
        "$scope",
        "$http",
        "CTX_PATH",
        function (
                $scope,
                $http,
                CTX_PATH) {

            $scope.day = {};

            $scope.saveDay = function () {

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
    ]);
})(window.angular, window.appConfig);
