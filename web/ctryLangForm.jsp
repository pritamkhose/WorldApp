
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>

    </head>
    <body >

        <%@ include file = "header.jsp" %>

    </center>
    <div align="center">
        <c:if test="${cnty != null}">
            <form action="CtryLangUpdate" method="post">
                <div class="row">
                    <div id="myDIVLeft" class="col-sm-3" align="left" >  
                        <button class="button buttonYellow" onclick="location.href = 'CtryLangList'" type="button">Back</button>
                        <button class="button buttonBlue" onclick="location.href = 'CtryLangNew'" type="button">New</button>
                    </div>
                    <div class="col-sm-6" align="center">
                        <h3>
                            <c:if test="${cnty != null}">
                                Edit Country Language
                            </c:if>
                            <c:if test="${cnty == null}">
                                Add New Country Language
                            </c:if>
                        </h3>
                    </div>
                    <div id="myDIVRight" class="col-sm-3" align="right" > 
                        <button class="button buttonRed" onclick="location.href = 'CtryLangDelete?code=<c:out value='${cnty.countryCode}' />'" type="button">Delete</button>
                        <input type="submit" value="Save" />
                    </div>
                </div>

            </c:if>

            <c:if test="${cnty == null}">
                <form action="CtryLangInsert" method="post">
                    <div class="row">
                        <div id="myDIVLeft" class="col-sm-3" align="left" >   
                            <button class="button buttonYellow" onclick="location.href = 'CtryLangList'" type="button">Back</button>
                            <button class="button buttonBlue" onclick="location.href = 'CtryLangNew'" type="button">New</button>
                        </div>
                        <div class="col-sm-6" align="center">
                            <h3>
                                <c:if test="${cnty != null}">
                                     Edit Country Language
                                </c:if>
                                <c:if test="${cnty == null}">
                                    Add New Country Language
                                </c:if>
                            </h3>
                        </div>
                        <div id="myDIVRight" class="col-sm-3" align="right" > 
                            <button class="button buttonRed" onclick="location.href = 'CtryLangDelete?code=<c:out value='${cnty.countryCode}' />'" type="button">Delete</button>
                            <input type="submit" value="Save" />
                        </div>
                    </div>
                </c:if>
                <table border="1" cellpadding="5">    
                     <tr>
                        <th>Country Code </th>
                        <td>
                            <input type="text" name="countryCode" size="35"  value="<c:out value='${cnty.countryCode}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Language: </th>
                        <td>
                            <input type="text" name="language" size="35"  value="<c:out value='${cnty.language}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Official Language: </th>
                        <td>
                            <input type="text" name="isOfficial" size="35"  value="<c:out value='${cnty.isOfficial}' />"  />
                        </td>
                    </tr>
                    <tr>
                        <th>Percentage </th>
                        <td>
                            <input type="text" name="percentage" size="35"  value="<c:out value='${cnty.percentage}' />"  />
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
