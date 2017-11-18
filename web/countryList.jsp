
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="js/autocompleter.js"></script>
        <link rel="stylesheet"   href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    </head>
    <body >

        <%@ include file = "header.jsp" %>

        <form>
            <div>
                <div class="row">
                    <div id="myDIVLeft" class="col-sm-3" align="left" >  
                        <button class="button buttonBlue" onclick="location.href = 'CountryNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-5" align="center">
                        <h4><b>List of Country</b></h4>
                    </div>
                    <div id="myDIVRight" class="col-sm-4" align="right" >
                        <div class="search-container">
                            <div class="ui-widget">
                                <input type="text" id="search" name="search" class="search"  placeholder="   Search  "/>
                            </div>
                        </div>
                        <!--                        <input type="text" name="search" placeholder="   Search  ">-->
                        <!--                          <input type="image" src="https://www.google.com/uds/css/v2/search_box_icon.png" class="gsc-search-button gsc-search-button-v2" title="search" name="search" placeholder="   Search  ">-->
                    </div>
                </div>
            </div>

        </form>

        <div style="overflow-x:auto;">
            <table>
                <!--            <caption><h2>Welcome</h2></caption>-->
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Continent</th>
                    <th>Region</th>
                    <th>Surface Area</th>
                    <th>Independence Year</th>
                    <th>Population</th>
                    <!--                <th>Life Expectancy</th>
                                    <th>GNP</th>
                                    <th>GNP Old</th>
                                    <th>Local Name</th>
                                    <th>Government Form</th>
                                    <th>Head Of State</th>-->
                    <th>Capital</th>
                    <th>Code2</th>
                    <th>  </th>
                </tr>
                <c:forEach var="cnty" items="${listCountry}">
                    <tr>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.code}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.name}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.continent}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.region}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.surfaceArea}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.indepYear}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.population}" /></a></td>
     <!--               <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.lifeExpectancy}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.GNP}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.GNPOld}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.localName}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.governmentForm}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.headOfState}" /></a></td>-->
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.capital}" /></a></td>
                        <td><a href="CountryEdit?code=<c:out value='${cnty.code}' />"><c:out value="${cnty.code2}" /></a></td>

                        <td>
                            <a background-color="red" href="CountryDelete?code=<c:out value='${cnty.code}' />">Delete</a>   
                        </td>
                        <!--                <span class="nowrap"><img src="dot.gif" title="Delete" alt="Delete" class="icon ic_b_drop">&nbsp;</span>-->
                    </tr>
                </c:forEach>
            </table>
        </div>	


        <%@ include file = "footer.jsp" %>

    </body>
</html>
