
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
                        <button class="button buttonBlue" onclick="location.href = 'CityNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-5" align="center">
                        <h4><b>List of City</b></h4>
                    </div>
                    <div id="myDIVRight" class="col-sm-4" align="right" >
                          <input type="text" name="search" placeholder="   Search  ">
                    </div>
                </div>
            </div>
          
        </form>

        <div style="overflow-x:auto;">
            <table>
                <!--            <caption><h2>Welcome</h2></caption>-->
                <tr>
                    <th>Name</th>
                    <th>Country Code</th>
                    <th>District</th>
                    <th>Population</th>
                    <th>  </th>
                </tr>
                <c:forEach var="datavar" items="${listData}">
                    <tr>
<!--                        <td><a href="CityEdit?id=<c:out value='${datavar.ID}' />"><c:out value="${datavar.ID}" /></a></td>-->
                        <td><a href="CityEdit?id=<c:out value='${datavar.ID}' />"><c:out value="${datavar.name}" /></a></td>
                        <td><a href="CityEdit?id=<c:out value='${datavar.ID}' />"><c:out value="${datavar.countryCode}" /></a></td>
                        <td><a href="CityEdit?id=<c:out value='${datavar.ID}' />"><c:out value="${datavar.district}" /></a></td>
                        <td><a href="CityEdit?id=<c:out value='${datavar.ID}' />"><c:out value="${datavar.population}" /></a></td>
                        <td>
                            <a background-color="red" href="CityDelete?code=<c:out value='${datavar.ID}' />">Delete</a>   
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>	


        <%@ include file = "footer.jsp" %>

    </body>
</html>
