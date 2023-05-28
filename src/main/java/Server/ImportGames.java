package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ImportGames{

    public static void importAll() throws FileNotFoundException {
        File dir = new File("E:\\AP\\Eighth-Assignment-Steam\\src\\main\\java\\Server\\Resources");
        File[] directoryListing = dir.listFiles();

        String url = "jdbc:postgresql://localhost:5432/Steam";
        String user = "postgres";
        String pass = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL database!");
            Statement statement = connection.createStatement();

            for (File file : directoryListing) {
                if (file.getName().endsWith(".txt")) {
                    Scanner reader = new Scanner(file);
                    String id = reader.nextLine();
                    String path = "E:\\AP\\Eighth-Assignment-Steam\\src\\main\\java\\Server\\Resources\\" + id + ".png";
                    String query = addQuotation(id);
                    while (reader.hasNextLine()) {
                        query += addQuotation(reader.nextLine());
                    }
                    query += addQuotation(path);
                    query = query.substring(0,query.length()-1);
                    statement.executeUpdate("INSERT INTO games VALUES (" + query + ")");
                }
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        importAll();
    }

    public static String addQuotation(String str) {
        return "'" + str + "'" + " ,";
    }
}
