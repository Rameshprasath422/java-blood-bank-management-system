package blood.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blood.ConfigReader;
import blood.DatabaseConnector;
import blood.ExceptionHandler;
public class AdminAuthentication {
    public static boolean authenticateAdmin(String username, String password) {
        boolean isAuthenticated = false;
        ConfigReader configReader = new ConfigReader();
        String query = configReader.getProperty("sql.admin.authenticateAdmin"); 
        try (Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {        
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);       
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isAuthenticated = true;
                }
            }
        } 
        catch (SQLException exception) 
        {
        	ExceptionHandler.handleException(exception);
        }
        return isAuthenticated;
    }
}
