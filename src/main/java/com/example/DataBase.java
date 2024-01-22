package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DataBase extends HttpServlet {
    public static final String TABLE = "CREATE TABLE users " +
            "(id serial not null, " +
            "name varchar(50) not null, " +
            "age integer not null, " +
            "PRIMARY KEY(id) " +
            ");";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("Creating database.....");

        try {
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            writer.println("Registration Driver failed...");
        }

        try (Connection connection = DriverManager.getConnection("jdbc:h2:testdb",
                "admin", "")) {
            Statement statement = connection.createStatement();
            writer.println("Connecting database successfully...");
            statement.executeUpdate(TABLE);
            statement.addBatch("INSERT INTO users (name, surname, age) values ('Vasya', 'Ivanov', 25)");
            statement.addBatch("INSERT INTO users (name, surname, age) values ('Petya', 'Petrov', 36)");
            statement.addBatch("INSERT INTO users (name, surname, age) values ('Oleg', 'Kruglov', 28)");
            statement.executeBatch();
            writer.println("Database has been created!");
            ResultSet resultSet = statement.executeQuery("SELECT name FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                writer.printf("%d. %s %d \n", id, name, age);
            }

        } catch (SQLException e) {
            writer.println("Connection failed...");
        }
    }
}
