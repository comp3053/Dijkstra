package utils;

import java.sql.*;

public class DatabaseHelper {
    private Connection c;
    private Statement statement = null;

    public DatabaseHelper() {
        this.connectSQLite();
    }

    public boolean connectSQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("SQLite connection successfully.");
        return true;
    }

    public void execSqlNoReturn(String query) throws SQLiteConnectionException {
        if (c == null) {
            // throw exception if SQLite not successful connect.
            throw new SQLiteConnectionException("SQLite connection error.");
        } else {

            try {
                statement = c.createStatement();
                statement.execute(query);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public ResultSet execSqlWithReturn(String query) throws SQLiteConnectionException {
        ResultSet rs = null;
        if (c == null) {
            // throw exception if SQLite not successful connect.
            throw new SQLiteConnectionException("SQLite connection error.");
        } else {
            try {
                statement = c.createStatement();
                rs = statement.executeQuery(query);
                // TODO: Check when to close statement
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }
    }

    public void execSqlUpdate(String updateQuery) throws SQLiteConnectionException {
        ResultSet rs = null;
        if (c == null) {
            // throw exception if SQLite not successful connect.
            throw new SQLiteConnectionException("SQLite connection error.");
        } else {
            try {
                statement = c.createStatement();
                statement.executeUpdate(updateQuery);
                c.commit();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
