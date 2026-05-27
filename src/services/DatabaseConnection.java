/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import expection.QueryTypeMismatchException;
import interfaces.IDatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utility.*;
import interfaces.IRowMapper;

/**
 *
 * @author farel
 */
public class DatabaseConnection implements IDatabaseConnection {

    public final static String SCHEME = "jdbc:mysql://";
    public final static String HOSTNAME = "localhost";
    public final static String DATABASE = "pbo_tubes";
    public final static int PORT = 3306;
    public final static String USERNAME = "root";
    public final static String PASSWORD = "";

    private Connection connection = null;

    public String getPath() {
        return SCHEME + HOSTNAME + ":" + PORT + "/" + DATABASE;
    }

    private void connect() throws SQLException {
        Log.create("Connecting to Database " + getPath());
        try {
            this.connection = DriverManager.getConnection(getPath(), USERNAME, PASSWORD);
            Log.create("Database " + DATABASE + " is Connected.");
        } catch (SQLException e) {
            Log.create(e.getMessage());
            throw e;
        }
    }

    private void disconnect() throws SQLException {
        Log.create("Disconnecting from Database " + getPath());
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                Log.create("Database successfully disconnected.");
            }
        } catch (SQLException e) {
            Log.create("Error disconnecting: " + e.getMessage());
            Log.create(e.getMessage());
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public <T> List<T> executeQuery(Query sql, IRowMapper<T> mapper) throws QueryTypeMismatchException {
        if (sql.queryType != Query.Type.SELECT) {
            throw new QueryTypeMismatchException(Query.Type.SELECT);
        }
        List<T> list = new ArrayList<>();
        try {
            connect();
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery(sql.build());
            
            while (result.next()) {
                list.add(mapper.mapRow(result));
            }

            disconnect();
            return list;
        } catch (SQLException e) {
            Log.create(e.getMessage());
        }
        return null;
    }
    
    @Override
    public int executeUpdate(Query sql) throws QueryTypeMismatchException {
        if (sql.queryType != Query.Type.UPDATE
                && sql.queryType != Query.Type.DELETE
                && sql.queryType != Query.Type.INSERT) {
            throw new QueryTypeMismatchException(Query.Type.UPDATE, Query.Type.INSERT, Query.Type.DELETE);
        }
        try {
            connect();
            Statement s = connection.createStatement();
            int result = s.executeUpdate(sql.toString());
            disconnect();
            return result;
        } catch (SQLException e) {
            Log.create(e.getMessage());
        }
        return 0;
    }

}
