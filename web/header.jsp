<%-- 
    Document   : header
    Created on : Sep 18, 2017, 12:17:13 AM
    Author     : Pritam
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>World Apps</title>
        <meta charset="utf-8">
        <!--   below line required frist time open website as per device , no need to default zoom -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--    <link rel="stylesheet" href="css/bootstrap.min.css">-->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">
            <%@include file="css/mystyle.css" %>
        </style>
    </head>
    <body>

        <nav class="navbar navbar-inverse">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp">WorldApp.com</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="CountryList" id="textWhite">Country</a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="CityList"  id="textWhite" >City <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="CityList"  id="textBlack">Country-wise</a></li>
                                <li><a href="CityListA"  id="textBlack">Alphabetically</a></li>
                            </ul>
                        </li>
                        <li><a href="CtryLangList"  id="textWhite">Country Language</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>



