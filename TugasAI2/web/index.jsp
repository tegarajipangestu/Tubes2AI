<%-- 
    Document   : index
    Created on : Nov 27, 2014, 12:08:56 AM
    Author     : tegar
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="classificator.news_classificator"%>
<%@page import="java.util.logging.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News Classificator</title>
    </head>
    <body>
        <h1>Hasilnya : </h1>
            <%
                news_classificator nc = new news_classificator();
                String[] Result = news_classificator.Learn(0);
            %>
            <p>
                <%
                    out.println(Result[0]);                
                %>
            </p>
            <p>
                <%
                    out.println(Result[1]);                    
                %>
            </p>
    </body>
</html>
