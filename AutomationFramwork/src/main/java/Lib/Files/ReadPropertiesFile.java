package Lib.Files;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = ReadPropertiesFile.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            properties.load(input);

            String dbUrl = properties.getProperty("database.url");
            String dbUsername = properties.getProperty("database.username");
            String dbPassword = properties.getProperty("database.password");

            // Print the values
            System.out.println("Database URL: " + dbUrl);
            System.out.println("Database Username: " + dbUsername);
            System.out.println("Database Password: " + dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
