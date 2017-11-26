package com.leafguard.companyserver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void createTable() {
        String sql = "CREATE TABEL IF NOT EXISTS plants (\n"
                + " id integer PRIMARY KEY, \n"
                + " plant_id integer NOT NULL, \n"
                + " moisture integer NOT NULL);";

    }


    public String getData() throws Exception{
        return "this is response from " + this.getClass() + " " + connection.getMetaData();
    }
 }
