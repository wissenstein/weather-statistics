<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath"
       value="${pageContext.request.servletContext.contextPath}"
       scope="request"/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      ng-app="weatherApp">
    <head>
        <meta charset="UTF-8" />
        <link href="${ctxPath}/resources/style/site.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            window.appConfig = {
                contextPath = ${ctxPath};
            };
        </script>
        <script src="${ctxPath}/resources/js/vendor/angular-1.4.1/angular.js" type="text/javascript"></script>
        <title>Weather Statistics. Administrators</title>
    </head>
    <body>
        <h1>Administrator panel</h1>
    </body>
</html>
