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
        <div>
            <h2><fmt:message key="keptData"/></h2>
            <div ng-controller="SaveDayController">
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
                                <td>${day.night}</td>
                                <td>${day.morning}</td>
                                <td>${day.midday}</td>
                                <td>${day.evening}</td>
                            </tr>
                        </c:forEach>
                        <form ng-submit="saveDay()">
                            <tr>
                                <td>
                                    <input type="text"
                                           name="dateString"
                                           ng-model="tmp.dateString"
                                           placeholder="yyyy-MM-dd"
                                           required="required" />
                                </td>
                                <td>
                                    <input type="number"
                                           name="night"
                                           ng-model="day.night"
                                           required="required" />
                                </td>
                                <td>
                                    <input type="number"
                                           name="morning"
                                           ng-model="day.morning"
                                           required="required" />
                                </td>
                                <td>
                                    <input type="number"
                                           name="midday"
                                           ng-model="day.midday"
                                           required="required" />
                                </td>
                                <td>
                                    <input type="number"
                                           name="enening"
                                           ng-model="day.evening"
                                           required="required" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <input type="submit" value="<fmt:message key="submit"/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <pre>{{day | json}}</pre>
                                </td>
                            </tr>
                        </form>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="${ctxPath}/resources/js/app/application.js" type="text/javascript"></script>
    </body>
</html>
