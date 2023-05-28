package Server;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:5432/Steam";
        String user = "postgres";
        String pass = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL database!");

            Statement statement = connection.createStatement();

            statement.close();
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
