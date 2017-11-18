<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </head>
    <body >

        <%@ include file = "header.jsp" %>

        <center>
            <h1>Error</h1>
            <h4><%=exception.getMessage()%><br/> </h4>
            <h4><%=exception.getStackTrace()%><br/> </h4>
            <br/> <br/>
            <button onclick="goBack()">Go Back</button>

        </center>	
        <%@ include file = "footer.jsp" %>

    </body>
</html>
