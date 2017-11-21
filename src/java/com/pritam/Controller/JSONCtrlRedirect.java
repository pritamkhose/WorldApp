/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pritam.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pritam.DAO.CityDAO;
import com.pritam.DAO.CountryDAO;
import com.pritam.DAO.CtryLangDAO;
import com.pritam.POJO.City;
import com.pritam.POJO.Country;
import com.pritam.POJO.CountryLang;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pritam
 */
public class JSONCtrlRedirect {

    public LinkedHashMap<String, Object> getCityList(HttpServletRequest request) throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        List<City> listData;
        String search;
        try {
            search = (request.getParameter("search")).replaceAll(" ", "");
        } catch (Exception e) {
            search = null;
        }
        CityDAO CityDAO = new CityDAO();
        if (search != null && search.length() > 0) {
            listData = CityDAO.listSearch(search);
        } else {
            listData = CityDAO.listAll();
        }

        data.put("total_results", listData.size());
        data.put("results", listData);
        return data;
    }

    public LinkedHashMap<String, Object> getCtryLangList(HttpServletRequest request) throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        List<CountryLang> listData;
        String search;
        try {
            search = (request.getParameter("search")).replaceAll(" ", "");
        } catch (Exception e) {
            search = null;
        }

        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        if (search != null && search.length() > 0) {
            listData = CtryLangDAO.listSearch(search);
        } else {
            listData = CtryLangDAO.listAll();
        }

        data.put("total_results", listData.size());
        data.put("results", listData);
        return data;
    }

    public LinkedHashMap<String, Object> getCountryList(HttpServletRequest request) throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        List<Country> listData;
        String search;
        CountryDAO countryDAO = new CountryDAO();
        try {
            search = (request.getParameter("search")).replaceAll(" ", "");
        } catch (Exception e) {
            search = null;
        }

        if (search != null && search.length() > 0) {
            listData = countryDAO.listSearchCountrys(search);
        } else {
            listData = countryDAO.listAllCountrys();
        }

        data.put("total_results", listData.size());
        data.put("results", listData);
        return data;
    }

    public LinkedHashMap<String, Object> showEditFormCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        CountryDAO countryDAO = new CountryDAO();
        Country existingCountry = countryDAO.getCountry(request.getParameter("code"));
        if (existingCountry != null) {
            data.put("ErrorID", 0);
            data.put("results", existingCountry);
            data.put("continent_list", countryDAO.getCountryList());
        } else {
            data.put("ErrorID", 50);
            data.put("ErrorString", "Not Data Found");
        }
        return data;

    }

    public LinkedHashMap<String, Object> deleteCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();

        Country country = new Country(request.getParameter("code"));
        CountryDAO countryDAO = new CountryDAO();
        countryDAO.deleteCountry(country);
        Country existingCountry = countryDAO.getCountry(request.getParameter("code"));
        if (existingCountry != null) {
            data.put("ErrorID", 0);
            data.put("results", existingCountry);
            data.put("continent_list", countryDAO.getCountryList());
        } else {
            data.put("ErrorID", 0);
            data.put("ErrorString", "Delete Sucessfuly");
        }
        return data;
    }

    public LinkedHashMap<String, Object> insertCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);//.append('\n');
            }
        } finally {
            reader.close();
        }

        if (sb != null && sb.length() > 1) {
            try {
                JsonObject json = new JsonParser().parse(sb.toString()).getAsJsonObject();
                JsonObject results = json.get("results").getAsJsonObject();
                //System.out.println("-->" + results.get("Code"));
                Country newCountry = new Country(
                        results.get("Code").getAsString(),
                        results.get("Name").getAsString(),
                        results.get("Continent").getAsString(),
                        results.get("Region").getAsString(),
                        results.get("SurfaceArea").getAsFloat(),
                        results.get("IndepYear").getAsInt(),
                        results.get("Population").getAsInt(),
                        results.get("LifeExpectancy").getAsFloat(),
                        results.get("GNP").getAsFloat(),
                        results.get("GNPOld").getAsFloat(),
                        results.get("LocalName").getAsString(),
                        results.get("GovernmentForm").getAsString(),
                        results.get("HeadOfState").getAsString(),
                        results.get("Capital").getAsInt(),
                        results.get("Code2").getAsString()
                );

                CountryDAO countryDAO = new CountryDAO();
                countryDAO.insertCountry(newCountry);
                response.sendRedirect("JSONCtrl?req=CountryEdit&code=" + request.getParameter("code"));
            } catch (Exception ex) {
                StackTraceElement[] stack = new Exception().getStackTrace();
                String theTrace = ex.toString() + "\n";
                for (StackTraceElement line : stack) {
                    theTrace += line.toString() + "\n";
                }
                data.put("ErrorID", 120);
                data.put("ErrorString", "JSON Parser Error");
                data.put("ErrorInfo", theTrace);
            }

        } else {
            data.put("ErrorID", 90);
            data.put("ErrorString", "Invalid insert request");
        }
       
        return data;
    }

    public LinkedHashMap<String, Object> updateCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);//.append('\n');
            }
        } finally {
            reader.close();
        }

        if (sb != null && sb.length() > 1) {
            try {
                JsonObject json = new JsonParser().parse(sb.toString()).getAsJsonObject();
                JsonObject results = json.get("results").getAsJsonObject();
                //System.out.println("-->" + results.get("Code"));
                Country newCountry = new Country(
                        results.get("Code").getAsString(),
                        results.get("Name").getAsString(),
                        results.get("Continent").getAsString(),
                        results.get("Region").getAsString(),
                        results.get("SurfaceArea").getAsFloat(),
                        results.get("IndepYear").getAsInt(),
                        results.get("Population").getAsInt(),
                        results.get("LifeExpectancy").getAsFloat(),
                        results.get("GNP").getAsFloat(),
                        results.get("GNPOld").getAsFloat(),
                        results.get("LocalName").getAsString(),
                        results.get("GovernmentForm").getAsString(),
                        results.get("HeadOfState").getAsString(),
                        results.get("Capital").getAsInt(),
                        results.get("Code2").getAsString()
                );

                CountryDAO countryDAO = new CountryDAO();
                countryDAO.updateCountry(newCountry);
                response.sendRedirect("JSONCtrl?req=CountryEdit&code=" + request.getParameter("code"));
            } catch (Exception ex) {
                StackTraceElement[] stack = new Exception().getStackTrace();
                String theTrace = ex.toString() + "\n";
                for (StackTraceElement line : stack) {
                    theTrace += line.toString() + "\n";
                }
                data.put("ErrorID", 120);
                data.put("ErrorString", "JSON Parser Error");
                data.put("ErrorInfo", theTrace);
            }

        } else {
            data.put("ErrorID", 90);
            data.put("ErrorString", "Invalid update Request");
        }
        return data;
    }

    /**
     * ****************************City****************************************
     */
    
    
    
    public LinkedHashMap<String, Object> getWholeCtryData(HttpServletRequest request)
            throws SQLException, IOException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>(); 

        List<CountryLang> CountryLanglistData;
        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        CountryLanglistData = CtryLangDAO.listSearch((request.getParameter("code")).replaceAll(" ", ""));
        
        List<City> CitylistData;
        CityDAO CityDAO = new CityDAO();
        CitylistData = CityDAO.listSearch((request.getParameter("code")).replaceAll(" ", ""));
            
        CountryDAO countryDAO = new CountryDAO();
        Country existingCountry = countryDAO.getCountry((request.getParameter("code")).replaceAll(" ", ""));
       
        if (existingCountry != null) {
            data.put("ErrorID", 0);
            data.put("country_details", existingCountry);
            data.put("continent_list", countryDAO.getCountryList());
            
            if (CountryLanglistData != null) {
            data.put("ErrorID", 0);
            data.put("ctryLang_details", CountryLanglistData);
            } else {
                data.put("ErrorID", 50);
                data.put("ErrorString", "Not Data Found");
            }
            
            if (CitylistData != null) {
            data.put("ErrorID", 0);
            Double total_city_population = 0.0d;
            for(int i=0; i < CitylistData.size();i++){
                try{
                total_city_population += CitylistData.get(i).getPopulation();
                } catch(Exception e){ 
                }
            }
            data.put("total_city_found", CitylistData.size());
            data.put("total_city_population", total_city_population);
            data.put("city_details", CitylistData);
           
            } else {
                data.put("ErrorID", 50);
                data.put("ErrorString", "Not Data Found");
            }
            
            
        } else {
            data.put("ErrorID", 50);
            data.put("ErrorString", "Not Data Found");
        }
        return data;

    }

}
