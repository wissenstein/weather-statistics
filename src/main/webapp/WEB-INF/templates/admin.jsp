<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                contextPath: "${ctxPath}"
            };
        </script>
        <script src="${ctxPath}/resources/js/vendor/angular-1.4.1/angular.js" type="text/javascript"></script>
        <title>Weather Statistics. Administrators</title>
    </head>
    <body>
        <h1><fmt:message key="administratorPanel"/></h1>
        <div ng-controller="SaveDayController">
            <form ng-submit="saveDay()" method="POST">
                <div>
                    <label for="year"><fmt:message key="year"/>:</label>
                    <input type="number" name="year"/>
                    <label for="month"><fmt:message key="month"/>:</label>
                    <input type="number" name="month"/>
                    <label for="day"><fmt:message key="day"/>:</label>
                    <input type="number" name="day"/>
                </div>
                <div>
                    <div><fmt:message key="temperature"/></div>
                    <div>
                        <label for="morning"><fmt:message key="atNight"/>:</label>
                        <input type="number" name="night"/>
                        <label for="morning"><fmt:message key="inMorning"/>:</label>
                        <input type="number" name="morning"/>
                        <label for="morning"><fmt:message key="atMidday"/>:</label>
                        <input type="number" name="midday"/>
                        <label for="morning"><fmt:message key="inEvening"/>:</label>
                        <input type="number" name="enening"/>
                    </div>
                </div>
                <div>
                    <input type="submit" value="<fmt:message key="submit"/>"/>
                </div>
            </form>
        </div>
        <script src="${ctxPath}/resources/js/app/application.js" type="text/javascript"></script>
    </body>
</html>
