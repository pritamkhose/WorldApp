package com.pritam.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pritam.DAO.AppConstant;
import com.pritam.DAO.CityDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pritam.POJO.Country;
import com.pritam.DAO.CountryDAO;
import com.pritam.DAO.CtryLangDAO;
import com.pritam.POJO.City;
import com.pritam.POJO.CountryLang;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * @author www.codejava.net
 * http://www.codejava.net/coding/jsp-servlet-jdbc-mysql-create-read-update-delete-crud-example
 */
public class AppController extends HttpServlet {

    //private static final long serialVersionUID = 1L;
    //private CountryDAO countryDAO;
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

    private void processrequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //printRequestPara(request); // http://localhost:8880/WorldApp/MCtrl?req=CityList
            switch (request.getServletPath()) {
                case "/CountryList":
                    listCountry(request, response);
                    break;
                /*case "/CountrytSearch":
                    CountrytSearch(request, response);
                    break;*/
                case "/CountryNew":
                    showNewFormCountry(request, response);
                    break;
                case "/CountryEdit":
                    showEditFormCountry(request, response);
                    break;
                case "/CountryInsert":
                    insertCountry(request, response);
                    break;
                case "/CountryUpdate":
                    updateCountry(request, response);
                    break;
                case "/CountryDelete":
                    deleteCountry(request, response);
                    break;

                case "/CityList":
                    CityList(request, response);
                    break;
                case "/CityListA":
                    CityListA(request, response);
                    break;
                case "/CityNew":
                    showNewFormCity(request, response);
                    break;
                case "/CityEdit":
                    showEditFormCity(request, response);
                    break;
                case "/CityInsert":
                    insertCity(request, response);
                    break;
                case "/CityUpdate":
                    updateCity(request, response);
                    break;
                case "/CityDelete":
                    deleteCity(request, response);
                    break;

                case "/CtryLangList":
                    CtryLangList(request, response);
                    break;
                case "/CtryLangNew":
                    showNewFormCtryLang(request, response);
                    break;
                case "/CtryLangEdit":
                    showEditFormCtryLang(request, response);
                    break;
                case "/CtryLangInsert":
                    insertCtryLang(request, response);
                    break;
                case "/CtryLangUpdate":
                    updateCtryLang(request, response);
                    break;
                case "/CtryLangDelete":
                    deleteCtryLang(request, response);
                    break;

                /* default: {
                    // Home Page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
                break;*/
            }
        } catch (SQLException ex) {
            System.out.println("ex --> " + ex);
            throw new ServletException(ex);

        }
    }

    /**
     * ****************************Country****************************************
     */
    private void listCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Country> listCountry;
        String search;
        CountryDAO countryDAO = new CountryDAO();
        try {
            search = (request.getParameter("search")).replaceAll(" ", " ");
        } catch (Exception e) {
            search = null;
        }

        if (search != null && search.length() > 0) {
            listCountry = countryDAO.listSearchCountrys(search);
        } else {
            listCountry = countryDAO.listAllCountrys();
        }

        request.setAttribute("listCountry", listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("countryList.jsp");
        dispatcher.forward(request, response);
        
        
    }

    private void showNewFormCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("countryForm.jsp");
        CountryDAO countryDAO = new CountryDAO();
        ArrayList<String> list = countryDAO.getCountryList();
        request.setAttribute("continentlist", list);
        dispatcher.forward(request, response);
    }

    private void showEditFormCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        CountryDAO countryDAO = new CountryDAO();
        Country existingCountry = countryDAO.getCountry(request.getParameter("code"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("countryForm.jsp");
        request.setAttribute("cnty", existingCountry);

        ArrayList<String> list = countryDAO.getCountryList();
        request.setAttribute("continentlist", list);
        dispatcher.forward(request, response);

    }

    private void insertCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Country newCountry = new Country(
                request.getParameter("code"),
                request.getParameter("name"),
                request.getParameter("continent"),
                request.getParameter("region"),
                Float.parseFloat(request.getParameter("surfaceArea")),
                Integer.parseInt(request.getParameter("indepYear")),
                Integer.parseInt(request.getParameter("population")),
                Float.parseFloat(request.getParameter("lifeExpectancy")),
                Float.parseFloat(request.getParameter("GNP")),
                Float.parseFloat(request.getParameter("GNPOld")),
                request.getParameter("localName"),
                request.getParameter("governmentForm"),
                request.getParameter("headOfState"),
                Integer.parseInt(request.getParameter("capital")),
                request.getParameter("code2")
        );
        CountryDAO countryDAO = new CountryDAO();
        countryDAO.insertCountry(newCountry);
        response.sendRedirect("CountryEdit?code=" + request.getParameter("code"));
    }

    private void updateCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Country newCountry = new Country(
                request.getParameter("code"),
                request.getParameter("name"),
                request.getParameter("continent"),
                request.getParameter("region"),
                Float.parseFloat(request.getParameter("surfaceArea")),
                Integer.parseInt(request.getParameter("indepYear")),
                Integer.parseInt(request.getParameter("population")),
                Float.parseFloat(request.getParameter("lifeExpectancy")),
                Float.parseFloat(request.getParameter("GNP")),
                Float.parseFloat(request.getParameter("GNPOld")),
                request.getParameter("localName"),
                request.getParameter("governmentForm"),
                request.getParameter("headOfState"),
                Integer.parseInt(request.getParameter("capital")),
                request.getParameter("code2")
        );
        CountryDAO countryDAO = new CountryDAO();
        countryDAO.updateCountry(newCountry);
        response.sendRedirect("CountryEdit?code=" + request.getParameter("code"));
    }

    private void deleteCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Country country = new Country(request.getParameter("code"));
        CountryDAO countryDAO = new CountryDAO();
        countryDAO.deleteCountry(country);
        response.sendRedirect("CountryList");
    }

    /**
     * ****************************City****************************************
     */
    private void CityList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<City> listData;
        String search;
        try {
            search = (request.getParameter("search")).replaceAll(" ", " ");
        } catch (Exception e) {
            search = null;
        }
        CityDAO CityDAO = new CityDAO();
        if (search != null && search.length() > 0) {
            listData = CityDAO.listSearch(search);
        } else {
            listData = CityDAO.listAll();
        }

        request.setAttribute("listData", listData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cityList.jsp");
        dispatcher.forward(request, response);
    }

    private void CityListA(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<City> listData;
        String search;
        try {
            search = (request.getParameter("search")).replaceAll(" ", " ");
        } catch (Exception e) {
            search = null;
        }
        CityDAO CityDAO = new CityDAO();
        if (search != null && search.length() > 0) {
            listData = CityDAO.listSearchA(search);
        } else {
            listData = CityDAO.listAllA();
        }

        request.setAttribute("listData", listData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cityList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormCity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cityForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        City existingCity = cityDAO.get(request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("cityForm.jsp");
        request.setAttribute("cnty", existingCity);
        dispatcher.forward(request, response);

    }

    private void insertCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        City newCity = new City(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                request.getParameter("countryCode"),
                request.getParameter("district"),
                Integer.parseInt(request.getParameter("population"))
        );
        CityDAO CityDAO = new CityDAO();
        CityDAO.insert(newCity);
        response.sendRedirect("CityList");
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        City newCity = new City(
                Integer.parseInt(request.getParameter("ID")),
                request.getParameter("name"),
                request.getParameter("countryCode"),
                request.getParameter("district"),
                Integer.parseInt(request.getParameter("population"))
        );
        CityDAO CityDAO = new CityDAO();
        CityDAO.update(newCity);
        response.sendRedirect("CityEdit?id=" + request.getParameter("ID"));
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        City City = new City(Integer.parseInt(request.getParameter("ID")));
        CityDAO cityDAO = new CityDAO();
        cityDAO.delete(City);
        response.sendRedirect("CityList");
    }

    /**
     * ****************************CountryLang****************************************
     */
    private void CtryLangList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<CountryLang> listData;
        String search;
        try {
            search = (request.getParameter("search")).replaceAll(" ", " ");
        } catch (Exception e) {
            search = null;
        }

        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        if (search != null && search.length() > 0) {
            listData = CtryLangDAO.listSearch(search);
        } else {
            listData = CtryLangDAO.listAll();
        }
        request.setAttribute("listData", listData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ctryLangList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormCtryLang(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ctryLangForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormCtryLang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        CountryLang existingCity = CtryLangDAO.get(request.getParameter("countryCode"), request.getParameter("language"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("ctryLangForm.jsp");
        request.setAttribute("cnty", existingCity);
        dispatcher.forward(request, response);

    }

    private void insertCtryLang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        CountryLang newCtryLang = new CountryLang(
                request.getParameter("countryCode"),
                request.getParameter("language"),
                request.getParameter("isOfficial"),
                Float.parseFloat(request.getParameter("percentage"))
        );
        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        CtryLangDAO.insert(newCtryLang);
        response.sendRedirect("CtryLangList");
    }

    private void updateCtryLang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        CountryLang newCtryLang = new CountryLang(
                request.getParameter("countryCode"),
                request.getParameter("language"),
                request.getParameter("isOfficial"),
                Float.parseFloat(request.getParameter("percentage"))
        );
        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        CtryLangDAO.update(newCtryLang);
        response.sendRedirect("CtryLangEdit?countryCode=" + request.getParameter("countryCode") + "&language=" + request.getParameter("language"));
    }

    private void deleteCtryLang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        CountryLang newCtryLang = new CountryLang(request.getParameter("countryCode"), request.getParameter("language"));
        CtryLangDAO CtryLangDAO = new CtryLangDAO();
        CtryLangDAO.delete(newCtryLang);
        response.sendRedirect("CtryLangList");
    }

    private void printRequestPara(HttpServletRequest request) {
        // get all parameter    
        HashMap<String, String> getallparams = new HashMap<String, String>();
        Map params = request.getParameterMap();
        Iterator iter = params.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String value = ((String[]) params.get(key))[0];
            getallparams.put(key, value);
        }
        System.out.println("Request Parameter --> " + getallparams.toString());
    }

    private void CountrytSearch(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            String term = request.getParameter("term");
            //System.out.println("Data from ajax call " + term);

            CountryDAO dataDao = new CountryDAO();
            ArrayList<String> list = dataDao.getFrameWork(term);

            String searchList = new Gson().toJson(list);
            response.getWriter().write(searchList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
