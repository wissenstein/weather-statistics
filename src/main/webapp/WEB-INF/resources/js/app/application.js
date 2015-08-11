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

            $scope.saveDay = function () {
                $http.post(CTX_PATH + "/admin/save-day", {test: "data"})
                .success(function (data) {
                    alert("success! " + data);
                })
                .error(function (error) {
                    alert("error! " + error);
                });
            };
        }
    ]);
})(window.angular, window.appConfig);
