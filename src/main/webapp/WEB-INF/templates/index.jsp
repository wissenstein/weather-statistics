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
        <title>Weather Statistics</title>
    </head>
    <body>
        <h1>Weather statistics</h1>
        <div>
            <table border="1" cellspacing="0">
                <col style="width:40%"/>
                <col style="width:15%"/>
                <col style="width:15%"/>
                <col style="width:15%"/>
                <col style="width:15%"/>
                <thead>
                    <tr>
                        <th rowspan="2"><fmt:message key="date"/></th>
                        <th colspan="4"><fmt:message key="temperatureInXarkiv"/></th>
                    </tr>
                    <tr>
                        <th><fmt:message key="atNight"/></th>
                        <th><fmt:message key="inMorning"/></th>
                        <th><fmt:message key="atMidday"/></th>
                        <th><fmt:message key="inEvening"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="day" items="${statistics}">
                        <tr>
                            <td>${day.date}</td>
                            <td>${day.nightTemperature}</td>
                            <td>${day.morningTemperature}</td>
                            <td>${day.dayTemperature}</td>
                            <td>${day.eveningTemperature}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="${ctxPath}/resources/js/app/application.js" type="text/javascript"></script>
    </body>
</html>
