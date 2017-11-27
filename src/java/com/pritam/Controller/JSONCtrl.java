/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pritam.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pritam.POJO.City;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.XML;
/**
 *
 * @author Pritam
 */
public class JSONCtrl extends HttpServlet {
     @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processrequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processrequest(request, response);
    }

    private void processrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        
         LinkedHashMap <String,Object> hmData=new LinkedHashMap <>();  
         //hmData.put("RequestURL",request.getRequestURL()); // request.getServletPath()+ request.getContextPath()
         JSONCtrlRedirect j = new JSONCtrlRedirect();
        try {
            switch (request.getParameter("req")) {
                case "CountryList":
                     hmData.put("ErrorID", 0);
                     hmData.put("data", j.getCountryList(request));
                     break;
                case "CountryEdit":
                    hmData.put("data", j.showEditFormCountry(request, response));
                    break;
                case "CountryInsert":
                    hmData.put("data", j.insertCountry(request, response));
                    break;
                case "CountryUpdate":
                    hmData.put("data", j.updateCountry(request, response));
                    break;
                case "CountryDelete":
                    hmData.put("data", j.deleteCountry(request, response));
                    break;
                    
                
                case "CityList":{ 
                        hmData.put("data",  j.getCityList(request));
                }
                break;
                case "CtryLangList":{ 
                        hmData.put("data", j.getCtryLangList(request));
                }
                break;
                
                case "WholeCtryData":{ 
                        hmData.put("data", j.getWholeCtryData(request));
                }
                break;
                
                
                default: {
                    hmData.put("ErrorID", 10);
                    hmData.put("ErrorString", "Not valid Request");
                }
                break;  
            }

        } catch (Exception ex) {
            StackTraceElement[] stack = new Exception().getStackTrace();
            String theTrace = ex.toString() + "\n";
            for(StackTraceElement line : stack)
            { 
               theTrace += line.toString()+"\n";
            }
             hmData.put("ErrorID", 20);
             hmData.put("ErrorString", "Error in request");
             hmData.put("ErrorInfo", theTrace);
        }
        
        
        PrintWriter out = response.getWriter();
//        if(request.getParameter("xml").equalsIgnoreCase("on")){
//            response.setContentType("text/xml");
//            response.setCharacterEncoding("UTF-8");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            JsonObject json = new JsonParser().parse(gson.toJson(hmData)).getAsJsonObject();
//            response.getWriter().write(XML.toString(json));
//        }
//        else
        {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.getWriter().write(gson.toJson(hmData));
        }
        out.flush();
        

    }
}
