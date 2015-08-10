(function (angular, appConfig) {
    angular.module("weatherApp", [])
    .constant("CTX_PATH", appConfig.contextPath);
})(window.angular, window.appConfig);
