
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>

    </head>
    <body >

        <%@ include file = "header.jsp" %>

    </center>
    <div align="center">
        <c:if test="${cnty != null}">
            <form action="CountryUpdate" method="post">
                <div class="row">
                    <div id="myDIVLeft" class="col-sm-3" align="left" >  
                        <button class="button buttonYellow" onclick="location.href = 'CountryList'" type="button">Back</button>
                        <button class="button buttonBlue" onclick="location.href = 'CountryNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-6" align="center">
                        <h3>
                            <c:if test="${cnty != null}">
                                Edit Country
                            </c:if>
                            <c:if test="${cnty == null}">
                                Add New Country
                            </c:if>
                        </h3>
                    </div>
                    <div id="myDIVRight" class="col-sm-3" align="right" > 
                        <button class="button buttonRed" onclick="location.href = 'CountryDelete?code=<c:out value='${cnty.code}' />'" type="button">Delete</button>
                        <input type="submit" value="Save" />
                    </div>
                </div>

            </c:if>

            <c:if test="${cnty == null}">
                <form action="CountryInsert" method="post">
                    <div class="row">
                        <div id="myDIVLeft" class="col-sm-3" align="left" >  
                            <button class="button buttonYellow" onclick="location.href = 'CountryList'" type="button">Back</button>
                            <button class="button buttonBlue" onclick="location.href = 'CountryNew'" type="button">New</button>
                        </div>
                        <div class="col-sm-6" align="center">
                            <h3>
                                <c:if test="${cnty != null}">
                                    Edit Country
                                </c:if>
                                <c:if test="${cnty == null}">
                                    Add New Country
                                </c:if>
                            </h3>
                        </div>
                        <div id="myDIVRight" class="col-sm-3" align="right" > 
                            <button class="button buttonRed" onclick="location.href = 'CountryDelete?code=<c:out value='${cnty.code}' />'" type="button">Delete</button>
                            <input type="submit" value="Save" />
                        </div>
                    </div>
                </c:if>
                <table border="1" cellpadding="5">
                    <c:if test="${cnty != null}">
    <!--                    <input type="hidden" name="code" value="<c:out value='${cnty.code}' />" />-->
                    </c:if>            
                    <tr>
                        <th>Code: </th>
                        <td>
                            <input type="text" name="code" size="35"  value="<c:out value='${cnty.code}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Name: </th>
                        <td>
                            <input type="text" name="name" size="35"  value="<c:out value='${cnty.name}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Continent: </th>
                        <td>
                            <select name="continent">
<!--                            <option value="Africa" ${cnty.continent == 'Africa' ? 'selected' : ''}>Africa</option>
                                <option value="Antarctica" ${cnty.continent == 'Antarctica' ? 'selected' : ''}>Antarctica</option>
                                -->
                                <c:forEach items="${continentlist}" var="continentlist">
                                    <option value="${continentlist}" ${cnty.continent == continentlist ? 'selected' : ''}>${continentlist}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>Region: </th>
                        <td>
                            <input type="text" name="region" size="35"  value="<c:out value='${cnty.region}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Surface Area: </th>
                        <td>
                            <input type="number" min="0" name="surfaceArea" size="35"  value="<c:out value='${cnty.surfaceArea}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Independence Year: </th>
                        <td>
                            <input type="number" min="0" max="2017"  name="indepYear" size="35"  value="<c:out value='${cnty.indepYear}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Population: </th>
                        <td>
                            <input type="number" min="0" name="population" size="35"  value="<c:out value='${cnty.population}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Life Expectancy: </th>
                        <td>
                            <input type="number" min="0"  step="0.01" name="lifeExpectancy" size="35"  value="<c:out value='${cnty.lifeExpectancy}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>GNP: </th>
                        <td>
                            <input type="number" min="0" step="0.01"  name="GNP" size="35"  value="<c:out value='${cnty.GNP}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>GNP Old: </th>
                        <td>
                            <input type="number" min="0" step="0.01" name="GNPOld" size="35"  value="<c:out value='${cnty.GNPOld}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Local Name: </th>
                        <td>
                            <input type="text" name="localName" size="35"  value="<c:out value='${cnty.localName}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Government Form: </th>
                        <td>
                            <input type="text" name="governmentForm" size="35"  value="<c:out value='${cnty.governmentForm}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Head of State: </th>
                        <td>
                            <input type="text" name="headOfState" size="35"  value="<c:out value='${cnty.headOfState}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Capital: </th>
                        <td>
                            <input type="number" min="0" max="999" name="capital" size="35"  value="<c:out value='${cnty.capital}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Code2: </th>
                        <td>
                            <input type="text" maxlength="3" name="code2" size="35"  value="<c:out value='${cnty.code2}' />"  />
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
