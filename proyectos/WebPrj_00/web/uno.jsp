<%-- 
    Document   : uno
    Created on : 10/09/2019, 12:20:46 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String strOpA, strOpB;
            int a, b, c;
            strOpA = request.getParameter("opA");
            strOpB = request.getParameter("opB");
            a = Integer.parseInt(strOpA);
            b = Integer.parseInt(strOpB);
            c = a + b;
        %>
        <%="El resultado es " + c +"<br>"%>
    </body>
</html>
