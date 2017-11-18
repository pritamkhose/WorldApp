
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>

    </head>
    <body >

        <%@ include file = "header.jsp" %>

        <form>
            <div>
                <div class="row">
                    <div id="myDIVLeft" class="col-sm-3" align="left" >  
                        <button class="button buttonBlue" onclick="location.href = 'CtryLangNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-5" align="center">
                        <h4><b>Country-wise Language List</b></h4>
                    </div>
                    <div id="myDIVRight" class="col-sm-4" align="right" >
                          <input type="text" name="search" placeholder="   Search  ">
                    </div>
                </div>
            </div>
          
        </form>

        <div style="overflow-x:auto;">
            <table>
                <tr>
                    <th>Country Code</th>
                    <th>Language</th>
                    <th>Is Official</th>
                    <th>Percentage</th>
                    <th>  </th>
                </tr>
                <c:forEach var="datavar" items="${listData}">
                    <tr>
                        <td><a href="CtryLangEdit?countryCode=<c:out value='${datavar.countryCode}' />&language=<c:out value='${datavar.language}' />"><c:out value="${datavar.countryCode}" /></a></td>
                        <td><a href="CtryLangEdit?countryCode=<c:out value='${datavar.countryCode}' />&language=<c:out value='${datavar.language}' />"><c:out value="${datavar.language}" /></a></td>
                        <td><a href="CtryLangEdit?countryCode=<c:out value='${datavar.countryCode}' />&language=<c:out value='${datavar.language}' />"><c:out value="${datavar.isOfficial}" /></a></td>
                        <td><a href="CtryLangEdit?countryCode=<c:out value='${datavar.countryCode}' />&language=<c:out value='${datavar.language}' />"><c:out value="${datavar.percentage}" /></a></td>
                        <td>
                            <a background-color="red" href="CtryLangDelete?code=<c:out value='${datavar.countryCode}' />&language=<c:out value='${datavar.language}' />">Delete</a>   
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>	


        <%@ include file = "footer.jsp" %>

    </body>
</html>
