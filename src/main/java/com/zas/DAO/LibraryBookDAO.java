//package com.zas.DAO;
//
//import models.LibraryBook;
//import services.ConnectionPool;
//import services.LoggerManager;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LibraryBookDAO {
//    private static final String INSERT_QUERY = "INSERT INTO LibraryBooks (bookID, title, author, copyNumber, availableCopies, readerID, year) VALUES (?, ?, ?, ?, ?, ?, ?)";
//    private static final String SELECT_ALL_QUERY = "SELECT * FROM LibraryBooks";
//    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM LibraryBooks WHERE bookID = ?";
//    private static final String UPDATE_QUERY = "UPDATE LibraryBooks SET title = ?, author = ?, copyNumber = ?, availableCopies = ?, readerID = ?, year = ? WHERE bookID = ?";
//    private static final String DELETE_QUERY = "DELETE FROM LibraryBooks WHERE bookID = ?";
//
//    public void addBook(LibraryBook book) {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getConnection();
//            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
//            statement.setInt(1, book.getId());
//            statement.setString(2, book.getTitle());
//            statement.setString(3, book.getAuthor());
//            statement.setInt(4, book.getCopyNumber());
//            statement.setInt(5, book.getAvailableCopies());
//            statement.setInt(6, book.getReaderId());
//            statement.setInt(7, book.getYear());
//            statement.executeUpdate();
//            ConnectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            LoggerManager.logException(e);
//            ConnectionPool.releaseConnection(connection);
//        }
//    }
//
//    public List<LibraryBook> getAllBooks() {
//        List<LibraryBook> books = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
//            while (resultSet.next()) {
//                int bookID = resultSet.getInt("bookID");
//                String title = resultSet.getString("title");
//                String author = resultSet.getString("author");
//                int year = resultSet.getInt("year");
//                int copyNumber = resultSet.getInt("copyNumber");
//                int availableCopies = resultSet.getInt("availableCopies");
//                int readerID = resultSet.getInt("readerID");
//                LibraryBook book = new LibraryBook.Builder(title, author, year, bookID)
//                        .build();
//                book.setCopyNumber(copyNumber);
//                book.setAvailableCopies(availableCopies);
//                book.setReaderId(readerID);
//                books.add(book);
//            }
//            ConnectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            LoggerManager.logException(e);
//            ConnectionPool.releaseConnection(connection);
//        }
//        return books;
//    }
//
//    public LibraryBook getBookById(int bookID) {
//        LibraryBook book = null;
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getConnection();
//            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
//            statement.setInt(1, bookID);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String title = resultSet.getString("title");
//                String author = resultSet.getString("author");
//                int year = resultSet.getInt("year");
//                int copyNumber = resultSet.getInt("copyNumber");
//                int availableCopies = resultSet.getInt("availableCopies");
//                int readerID = resultSet.getInt("readerID");
//                book = new LibraryBook.Builder(title, author, year, bookID)
//                        .build();
//                book.setCopyNumber(copyNumber);
//                book.setAvailableCopies(availableCopies);
//                book.setReaderId(readerID);
//            }
//            ConnectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            LoggerManager.logException(e);
//            ConnectionPool.releaseConnection(connection);
//        }
//        return book;
//    }
//
//    public void updateBook(LibraryBook book) {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getConnection();
//            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
//            statement.setString(1, book.getTitle());
//            statement.setString(2, book.getAuthor());
//            statement.setInt(3, book.getCopyNumber());
//            statement.setInt(4, book.getAvailableCopies());
//            statement.setInt(5, book.getReaderId());
//            statement.setInt(6, book.getYear());
//            statement.setInt(7, book.getId());
//            statement.executeUpdate();
//            ConnectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            LoggerManager.logException(e);
//            ConnectionPool.releaseConnection(connection);
//        }
//    }
//
//    public void deleteBook(int bookID) {
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getConnection();
//            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
//            statement.setInt(1, bookID);
//            statement.executeUpdate();
//            ConnectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            LoggerManager.logException(e);
//            ConnectionPool.releaseConnection(connection);
//        }
//    }
//}