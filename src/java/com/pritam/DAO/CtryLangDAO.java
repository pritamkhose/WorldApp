package com.pritam.DAO;

import com.pritam.POJO.CountryLang;
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
 * table countrylanguage in the database.
 *
 * @author www.codejava.net
 *
 */
public class CtryLangDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CtryLangDAO() {
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

    public boolean insert(CountryLang countrylanguage) throws SQLException {
        String sql = "INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, countrylanguage.getCountryCode());
        statement.setString(2, countrylanguage.getLanguage());
        statement.setString(3, countrylanguage.getIsOfficial());
        statement.setFloat(4, countrylanguage.getPercentage());
       

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<CountryLang> listAll() throws SQLException {
        List<CountryLang> listCountryLang = new ArrayList<>();

        String sql = "SELECT * FROM countrylanguage ORDER BY CountryCode ASC LIMIT 50 ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

            CountryLang countrylanguage = new CountryLang(
                    resultSet.getString("CountryCode"),
                    resultSet.getString("Language"),
                    resultSet.getString("IsOfficial"),
                    resultSet.getFloat("Percentage")
            );
            listCountryLang.add(countrylanguage);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCountryLang;
    }

    public List<CountryLang> listSearch(String search) throws SQLException {
        List<CountryLang> listCountryLang = new ArrayList<>();

        String sql = "SELECT * FROM countrylanguage where CountryCode LIKE '" + search + "%' ORDER BY CountryCode ASC LIMIT 50 ";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {

              CountryLang countrylanguage = new CountryLang(
                    resultSet.getString("CountryCode"),
                    resultSet.getString("Language"),
                    resultSet.getString("IsOfficial"),
                    resultSet.getFloat("Percentage")
            );
            listCountryLang.add(countrylanguage);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCountryLang;
    }

    public boolean delete(CountryLang countrylanguage) throws SQLException {
        String sql = "DELETE FROM countrylanguage where CountryCode = ? and Language = ? ";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, countrylanguage.getCountryCode());
        statement.setString(2, countrylanguage.getLanguage());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean update(CountryLang countrylanguage) throws SQLException {

        String sql = " UPDATE countrylanguage SET "
                + " Language = '" + countrylanguage.getLanguage() + "', "
                + " IsOfficial = '" + countrylanguage.getIsOfficial() + "', "
                + " Percentage = " + countrylanguage.getPercentage() + " "
              
                + " WHERE CountryCode = '" + countrylanguage.getCountryCode()
                +"' and Language = '" + countrylanguage.getLanguage() +"' ; ";
        
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        boolean rowUpdated = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowUpdated;
    }

    public CountryLang get(String Code, String Lang) throws SQLException {
        CountryLang countrylanguage = null;
        String sql = "SELECT * FROM countrylanguage WHERE CountryCode = ? and Language = ? ";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, Code);
        statement.setString(2, Lang);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
             countrylanguage = new CountryLang(
                    resultSet.getString("CountryCode"),
                    resultSet.getString("Language"),
                    resultSet.getString("IsOfficial"),
                    resultSet.getFloat("Percentage")
            );
        }

        resultSet.close();
        statement.close();

        return countrylanguage;
    }
}
