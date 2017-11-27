/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pritam.DAO;

/**
 *
 * @author Pritam
 */
public class AppConstant {
    
    public static String jdbcClass = "com.mysql.jdbc.Driver";
   
//    public static String jdbcURL = "jdbc:mysql://localhost:3306/world";
//    public static String jdbcUsername = "root";
//    public static String jdbcPassword = "root";
    
    public static String dbName = "pritam";
    public static String jdbcURL = "jdbc:mysql://webserver.ckczvbrr9h31.us-east-1.rds.amazonaws.com:3306/"+dbName;
    public static String jdbcUsername = "pritam";
    public static String jdbcPassword = "pritam123";

}
