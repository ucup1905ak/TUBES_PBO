/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.database.*;
import interfaces.IDatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utility.*;
import interfaces.IRowMapper;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class DatabaseConnection implements IDatabaseConnection {

    public final static String SCHEME = "jdbc:mysql://";
    public final static String HOSTNAME = "localhost";
    public final static String DATABASE = "pbo_tubes";
    public final static int PORT = 3306;
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";

    private Connection connection = null;

    public String getPath() {
        return SCHEME + HOSTNAME + ":" + PORT + "/" + DATABASE;
    }

    private void connect() throws DatabaseException {
        Log.create("Connecting to Database " + getPath());
        try {
            this.connection = DriverManager.getConnection(getPath(), USERNAME, PASSWORD);
            Log.create("Database " + DATABASE + " is Connected.");
        } catch (SQLException e) {
            Log.err(e.getMessage());
            throw new DatabaseConnectionFailedException("Connection Failed.", e);
        }
    }

    private void disconnect() throws DatabaseException {
        Log.create("Disconnecting from Database " + getPath());
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                Log.create("Database successfully disconnected.");
            }
        } catch (SQLException e) {
            Log.err(e.getMessage());
            throw new DatabaseConnectionFailedException("Disconnect Failed.", e);
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
    public <T> List<T> executeQuery(Query sql, IRowMapper<T> mapper) throws DatabaseException {
        if (sql.queryType != Query.Type.SELECT) {
            throw new QueryTypeMismatchException(Query.Type.SELECT);
        }
        List<T> list = new ArrayList<>();
        try {
            connect();
            Statement s = connection.createStatement();
            ResultSet result = s.executeQuery(sql.build());

            while (result.next()) {
                list.add(mapper.map(result));
            }

            disconnect();
            Log.create("Queried " +list.size()+ " row." );
        } catch (SQLException e) {
            Log.err(e.getMessage());
            throw new QueryExecutionException(sql.toString(), e);
        } finally {
            return list;
        }
    }

    @Override
    public int executeUpdate(Query sql) throws DatabaseException {
        if (sql.queryType != Query.Type.UPDATE
                && sql.queryType != Query.Type.DELETE
                && sql.queryType != Query.Type.INSERT) {
            throw new QueryTypeMismatchException(Query.Type.UPDATE, Query.Type.INSERT, Query.Type.DELETE);
        }
        int result = 0;
        try {
            connect();
            Statement s = connection.createStatement();
            result = s.executeUpdate(sql.toString());
            disconnect();
            Log.create("Updated " +result+ " row." );
        } catch (SQLException e) {
            Log.err(e.getMessage());
            throw new QueryExecutionException(sql.toString(), e);
        } finally {
            return result;
        }
    }

}
