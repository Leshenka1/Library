package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static Connection connection = null;

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:postgresql://localhost:5432/db"; // Здесь указывается ваш URL базы данных
    private static final String USER = "postgres"; // Замените на свое имя пользователя
    private static final String PASSWORD = "pass"; // Замените на свой пароль

    // Метод для получения соединения с базой данных
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Загружаем драйвер JDBC для базы данных (например, MySQL)
             //   Class.forName("com.mysql.cj.jdbc.Driver"); // Убедитесь, что драйвер JDBC соответствует вашей СУБД

                // Создаем соединение
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                // Обработка ошибок соединения
                throw new RuntimeException("Failed to connect to the database.");
            }
        }
        return connection;
    }

    // Метод для закрытия соединения
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Обработка ошибок при закрытии соединения
            }
        }
    }
}