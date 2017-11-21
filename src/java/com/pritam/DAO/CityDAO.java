package com.pritam.DAO;

import com.pritam.POJO.City;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table city in the database.
 *
 * @author www.codejava.net
 *
 */
public class CityDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CityDAO() {
        this.jdbcURL = AppConstant.jdbcURL;
        this.jdbcUsername = AppConstant.jdbcUsername;
        this.jdbcPassword = AppConstant.jdbcPassword;
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(AppConstant.jdbcClass);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insert(City city) throws SQLException {
        String sql = "INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, city.getID ());
        statement.setString(2, city.getName());
        statement.setString(3, city.getCountryCode());
        statement.setString(4, city.getDistrict());
        statement.setInt(5, city.getPopulation());
     

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<City> listAll() throws SQLException {
        List<City> listCity = new ArrayList<>();

        String sql = "SELECT * FROM city ORDER BY CountryCode ASC LIMIT 50  ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

            City city = new City(
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
            listCity.add(city);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCity;
    }

    public List<City> listSearch(String search) throws SQLException {
        List<City> listCity = new ArrayList<>();

        String sql = "SELECT * FROM city where Name LIKE '" + search + "%' or CountryCode LIKE '" + search + "%'or District LIKE '" + search + "%'  ORDER BY CountryCode ASC LIMIT 5000  ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

             City city = new City(
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
            listCity.add(city);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCity;
    }
    
    
       public List<City> listAllA() throws SQLException {
        List<City> listCity = new ArrayList<>();

        String sql = "SELECT * FROM city ORDER BY Name ASC LIMIT 50  ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

            City city = new City(
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
            listCity.add(city);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCity;
    }

    public List<City> listSearchA(String search) throws SQLException {
        List<City> listCity = new ArrayList<>();

        String sql = "SELECT * FROM city where Name LIKE '" + search + "%' or CountryCode LIKE '" + search + "%'or District LIKE '" + search + "%'  ORDER BY Name ASC LIMIT 50  ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

             City city = new City(
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
            listCity.add(city);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCity;
    }

    public boolean delete(City city) throws SQLException {
        String sql = "DELETE FROM city where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, city.getID());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean update(City city) throws SQLException {

        String sql = " UPDATE city SET "
                + " Name = '" + city.getName() + "', "
                + " CountryCode = '" + city.getCountryCode() + "', "
                + " District = '" + city.getDistrict() + "', "
                + " Population = " + city.getPopulation() + " "
                + " WHERE ID = " + city.getID() + " ; ";
        //System.out.println("newCity --> " + city.getCode() + " - "+ sql);

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     

        boolean rowUpdated = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowUpdated;
    }

    public City get(String Code) throws SQLException {
        City city = null;
        String sql = "SELECT * FROM city WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, Code);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            city = new City(
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
        }

        resultSet.close();
        statement.close();

        return city;
    }
}
