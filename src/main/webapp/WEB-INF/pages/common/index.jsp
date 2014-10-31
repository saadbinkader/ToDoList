<%--
  Created by IntelliJ IDEA.
  User: saad
  Date: 8/31/13
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>

<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>To Do</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/StyleSheet.css'/>">
    <style>
        table, td, th {
            border:1px solid orange;
            border-collapse:collapse;
        }
        th {
            background-color:orange;
            color:white;
        }
    </style>
</head>

<body>

<table>
    <tr>
        <th>User : ${user.userName}</th>
    </tr>

    <tr>
        <td>Day : Today</td>
    </tr>

    <tr>
        <td>
            <table style="padding: 10px;">
                <tr>
                    <td>
                        <form:form method="post" action="user/add" commandName="task">
                                <form:input path="taskTitle"/>
                                <input type="submit" value="Add"/>
                        </form:form>
                    </td>
                </tr>

                <tr>

                    <table>
                        <c:if test="${taskList ne null} ">
                            <tr>
                                <th>ID</th> <th>Task</th> <th>Time</th>
                            </tr>
                        </c:if>
                    <c:forEach items="${taskList}" var="task">
                        <tr>
                            <td style="color: black">[${task.taskID}]</td>
                            <td style="color: green">[${task.taskTitle}]</td>
                            <td style="color: blue">[${task.taskTimeStamp.time}]</td>
                            <td>
                                <form action="user/delete/${task.taskID}" method="post"><input type="submit" value="Delete"/></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>

                </tr>

                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${user.userName eq 'Default'}">
                                <form:form method="post" action="user/login" commandName="user">
                                    User:<form:input path="emailID"/>
                                    Password:<form:input path="password"/>
                                    <input type="submit" value="login"/>
                                </form:form>
                            </c:when>
                            <c:otherwise>
                                <form action="user/logout" method="get"><input type="submit" value="logout"/></form>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>

            </table>
        </td>
    </tr>
</table>

</body>
</html>