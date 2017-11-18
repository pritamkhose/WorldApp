
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    </center>
    <div align="center">
        <c:if test="${cnty != null}">
            <form action="CityUpdate" method="post">
                <div class="row">
                    <div id="myDIVLeft" class="col-sm-3" align="left" >  
                        <button class="button buttonYellow" onclick="goBack()" type="button">Back</button>
                        <button class="button buttonBlue" onclick="location.href = 'CityNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-6" align="center">
                        <h3>
                            <c:if test="${cnty != null}">
                                Edit City
                            </c:if>
                            <c:if test="${cnty == null}">
                                Add New City
                            </c:if>
                        </h3>
                    </div>
                    <div id="myDIVRight" class="col-sm-3" align="right" > 
                        <button class="button buttonRed" onclick="location.href = 'CityDelete?code=<c:out value='${cnty.ID}' />'" type="button">Delete</button>
                        <input type="submit" value="Save" />
                    </div>
                </div>

            </c:if>

            <c:if test="${cnty == null}">
                <form action="CityInsert" method="post">
                    <div class="row">
                        <div id="myDIVLeft" class="col-sm-3" align="left" >   
                            <c:choose>
                                <c:when test="${empty back}">
                                    <button class="button buttonYellow" onclick="location.href = 'CityListA'" type="button">Back</button>

                                </c:when>
                                <c:otherwise>
                                    <button class="button buttonYellow" onclick="location.href = 'CityList'" type="button">Back</button>

                                </c:otherwise>
                            </c:choose>
                            <button class="button buttonBlue" onclick="location.href = 'CityNew'" type="button">New</button>
                        </div>
                        <div class="col-sm-6" align="center">
                            <h3>
                                <c:if test="${cnty != null}">
                                    Edit City
                                </c:if>
                                <c:if test="${cnty == null}">
                                    Add New City
                                </c:if>
                            </h3>
                        </div>
                        <div id="myDIVRight" class="col-sm-3" align="right" > 
                            <button class="button buttonRed" onclick="location.href = 'CityDelete?code=<c:out value='${cnty.ID}' />'" type="button">Delete</button>
                            <input type="submit" value="Save" />
                        </div>
                    </div>
                </c:if>
                <table border="1" cellpadding="5">
                    <c:if test="${cnty != null}">
                        <input type="hidden" name="ID" value="<c:out value='${cnty.ID}' />" />
                    </c:if>            
                    <tr>
                        <th>Name: </th>
                        <td>
                            <input type="text" name="name" size="35"  value="<c:out value='${cnty.name}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Country Code: </th>
                        <td>
                            <input type="text" name="countryCode" size="35"  value="<c:out value='${cnty.countryCode}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>District: </th>
                        <td>
                            <input type="text" name="district" size="35"  value="<c:out value='${cnty.district}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Population: </th>
                        <td>
                            <input type="text" name="population" size="35"  value="<c:out value='${cnty.population}' />"  />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                    <center>
                        <input type="submit" value="Save" />
                    </center> 
                    </td>
                    </tr>
                </table>
            </form>


            <%@ include file = "footer.jsp" %>

            </body>
            </html>
