package com.leafguard.companyserver;

import java.sql.*;

public class DataStore
{

    Connection connection = null;

    public DataStore()
    {
        try {
            String url = "jdbc:sqlite:src/com/leafguard/companyserver/leafguard.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(connection != null) {
                    DatabaseMetaData meta = connection.getMetaData();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getData() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM logs");
        String ret = "";
        while (res.next()) {
            ret += " "+res.getString("name");
        }
        return ret;
    }

    public void closeConnection() throws Exception{
        this.connection.close();
    }
  }
