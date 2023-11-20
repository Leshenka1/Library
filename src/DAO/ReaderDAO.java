package DAO;

import models.Reader;
import services.ConnectionPool;
import services.LoggerManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    private static final String INSERT_QUERY = "INSERT INTO Readers (readerID, name, age, hasoverduefees, feesdays) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Readers";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Readers WHERE readerID = ?";
    private static final String UPDATE_QUERY = "UPDATE Readers SET name = ?, age = ?, hasoverduefees = ?, feesdays = ? WHERE readerID = ?";
    private static final String DELETE_QUERY = "DELETE FROM Readers WHERE readerID = ?";

    public void addReader(Reader reader) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setInt(1, reader.getId());
            statement.setString(2, reader.getName());
            statement.setInt(3, reader.getAge());
            statement.setBoolean(4, reader.getHasOverdueFees());
            statement.setInt(5, reader.getFeesDays());
            statement.executeUpdate();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            LoggerManager.logException(e);
            ConnectionPool.releaseConnection(connection);
        }
    }

    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                int readerID = resultSet.getInt("readerid");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                boolean hasOverdueFees = resultSet.getBoolean("hasoverduefees");
                int feesDays = resultSet.getInt("feesdays");
                Reader reader = new Reader.Builder(name, age, readerID).build();
                reader.setHasOverdueFees(hasOverdueFees);
                reader.setFeesDays(feesDays);
                readers.add(reader);
            }
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            LoggerManager.logException(e);
            ConnectionPool.releaseConnection(connection);
        }
        return readers;
    }

    public Reader getReaderById(int readerID) {
        Reader reader = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, readerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                boolean hasOverdueFees = resultSet.getBoolean("hasoverduefees");
                int feesDays = resultSet.getInt("feesdays");
                reader = new Reader.Builder(name, age, readerID).build();
                reader.setHasOverdueFees(hasOverdueFees);
                reader.setFeesDays(feesDays);
            }
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            LoggerManager.logException(e);
            ConnectionPool.releaseConnection(connection);
        }
        return reader;
    }

    public void updateReader(Reader reader) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, reader.getName());
            statement.setInt(2, reader.getAge());
            statement.setBoolean(3, reader.getHasOverdueFees());
            statement.setInt(4, reader.getFeesDays());
            statement.setInt(5, reader.getId());
            statement.executeUpdate();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            LoggerManager.logException(e);
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void deleteReader(int readerID) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, readerID);
            statement.executeUpdate();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            LoggerManager.logException(e);
            ConnectionPool.releaseConnection(connection);
        }
    }
}