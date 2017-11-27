package com.pritam.DAO;

import com.pritam.POJO.Country;
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
 * table country in the database.
 *
 * @author www.codejava.net
 *
 */
public class CountryDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CountryDAO() {
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

    public boolean insertCountry(Country country) throws SQLException {
        String sql = "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, country.getCode());
        statement.setString(2, country.getName());
        statement.setString(3, country.getContinent());
        statement.setString(4, country.getRegion());
        statement.setFloat(5, country.getSurfaceArea());
        statement.setInt(6, country.getIndepYear());
        statement.setInt(7, country.getPopulation());
        statement.setFloat(8, country.getLifeExpectancy());
        statement.setFloat(9, country.getGNP());
        statement.setFloat(10, country.getGNPOld());
        statement.setString(11, country.getLocalName());
        statement.setString(12, country.getGovernmentForm());
        statement.setString(13, country.getHeadOfState());
        statement.setInt(14, country.getCapital());
        statement.setString(15, country.getCode2());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Country> listAllCountrys() throws SQLException {
        List<Country> listCountry = new ArrayList<>();

        String sql = "SELECT * FROM country";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

            Country country = new Country(
                    resultSet.getString("Code"),
                    resultSet.getString("Name"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Region"),
                    resultSet.getFloat("SurfaceArea"),
                    resultSet.getInt("IndepYear"),
                    resultSet.getInt("Population"),
                    resultSet.getFloat("LifeExpectancy"),
                    resultSet.getFloat("GNP"),
                    resultSet.getFloat("GNPOld"),
                    resultSet.getString("LocalName"),
                    resultSet.getString("GovernmentForm"),
                    resultSet.getString("HeadOfState"),
                    resultSet.getInt("Capital"),
                    resultSet.getString("Code2")
            );
            listCountry.add(country);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCountry;
    }

    public List<Country> listSearchCountrys(String search) throws SQLException {
        List<Country> listCountry = new ArrayList<>();

        String sql = "SELECT * FROM country where name LIKE '" + search + "%' ";

        //
//        int columnCount = rsmd.getColumnCount();
//
//        // The column count starts from 1 
//        for (int i = 1; i <= columnCount; i++) {
//            String name = rsmd.getColumnName(i);
//            // Do stuff with name 
//            System.out.println(" --> " + name);
//        }
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

            Country country = new Country(
                    resultSet.getString("Code"),
                    resultSet.getString("Name"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Region"),
                    resultSet.getFloat("SurfaceArea"),
                    resultSet.getInt("IndepYear"),
                    resultSet.getInt("Population"),
                    resultSet.getFloat("LifeExpectancy"),
                    resultSet.getFloat("GNP"),
                    resultSet.getFloat("GNPOld"),
                    resultSet.getString("LocalName"),
                    resultSet.getString("GovernmentForm"),
                    resultSet.getString("HeadOfState"),
                    resultSet.getInt("Capital"),
                    resultSet.getString("Code2")
            );
            listCountry.add(country);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCountry;
    }

    public boolean deleteCountry(Country country) throws SQLException {
        String sql = "DELETE FROM country where Code = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, country.getCode());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateCountry(Country country) throws SQLException {

        String sql = " UPDATE country SET "
                + " Name = '" + country.getName() + "', "
                + " Continent = '" + country.getContinent() + "', "
                + " Region = '" + country.getRegion() + "', "
                + " SurfaceArea = " + country.getSurfaceArea() + ", "
                + " IndepYear = " + country.getIndepYear() + ", "
                + " Population = " + country.getPopulation() + ", "
                + " LifeExpectancy = " + country.getLifeExpectancy() + ", "
                + " GNP = " + country.getGNP() + ", "
                + " GNPOld = " + country.getGNPOld() + ", "
                + " LocalName = '" + country.getLocalName() + "', "
                + " GovernmentForm = '" + country.getGovernmentForm() + "', "
                + " HeadOfState = '" + country.getHeadOfState() + "', "
                + " Capital = " + country.getCapital() + ", "
                + " Code2 = '" + country.getCode2() + "' "
                + " WHERE Code = '" + country.getCode() + "' ; ";
        System.out.println("newCountry --> " + country.getCode() + " - "+ sql);

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        /* 
        sql = "UPDATE country SET Name= ?, Continent= ?, Region= ?, SurfaceArea= ?, IndepYear= ?, Population= ?, LifeExpectancy= ?, GNP= ?, GNPOld= ?, LocalName= ?, GovernmentForm= ?, HeadOfState= ?, Capital= ?, Code2= ? ";
        sql += " WHERE Code = ?";
        statement.setString(1, country.getCode());
        statement.setString(2, country.getName());
        statement.setString(3, country.getContinent());
        statement.setString(4, country.getRegion());
        statement.setFloat(5, country.getSurfaceArea());
        statement.setInt(6, country.getIndepYear());
        statement.setInt(7, country.getPopulation());
        statement.setFloat(8, country.getLifeExpectancy());
        statement.setFloat(9, country.getGNP());
        statement.setFloat(10, country.getGNPOld());
        statement.setString(11, country.getLocalName());
        statement.setString(12, country.getGovernmentForm());
        statement.setString(13, country.getHeadOfState());
        statement.setInt(14, country.getCapital());
        statement.setString(15, country.getCode2());*/

        boolean rowUpdated = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Country getCountry(String Code) throws SQLException {
        Country country = null;
        String sql = "SELECT * FROM country WHERE Code = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, Code);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            country = new Country(
                    resultSet.getString("Code"),
                    resultSet.getString("Name"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Region"),
                    resultSet.getFloat("SurfaceArea"),
                    resultSet.getInt("IndepYear"),
                    resultSet.getInt("Population"),
                    resultSet.getFloat("LifeExpectancy"),
                    resultSet.getFloat("GNP"),
                    resultSet.getFloat("GNPOld"),
                    resultSet.getString("LocalName"),
                    resultSet.getString("GovernmentForm"),
                    resultSet.getString("HeadOfState"),
                    resultSet.getInt("Capital"),
                    resultSet.getString("Code2")
            );
        }

        resultSet.close();
        statement.close();

        return country;
    }

    public ArrayList<String> getCountryList() throws SQLException {
        ArrayList list = new ArrayList<String>();
        String sql = "SELECT distinct Continent FROM country order by Continent asc; ";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
      
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
             list.add(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();

        return list;
    }

    public ArrayList<String> getFrameWork(String search) throws SQLException {
         ArrayList list = new ArrayList<String>();
        String sql = "SELECT Name FROM country where name LIKE '" + search + "%' ";

     
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {
            list.add(resultSet.getString("Name"));
        }

        resultSet.close();
        statement.close();

        disconnect();

        return list;
    }
}
