package com.example;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/store?user=postgres&password=123";
    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            String table = "CREATE TABLE users " +
                    "(id serial not null, " +
                    "name varchar(50) not null, " +
                    "age integer not null, " +
                    "PRIMARY KEY(id) " +
                    ");";
            statement.executeUpdate(table);

        } catch (SQLException e) {
            System.out.println("Connection failed...");
            e.printStackTrace();
        }
    }

    public static void insertUsers() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO users (name, age) VALUES " +
                    "('Tom', 23)," +
                    "('Mark', 35)";
            int numRows = statement.executeUpdate(sql);
            System.out.println("Count rows: " + numRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> select() {
        ArrayList<User> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                User user = new User(id, name, age);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void getResult(ArrayList<User> list, PrintWriter writer) {
        for (User user: list) {
            writer.println("User id: " + user.getId() + " ; " + "User name: " + user.getName() +
                    " ; " + "User age: " + user.getAge());
        }
    }
}
