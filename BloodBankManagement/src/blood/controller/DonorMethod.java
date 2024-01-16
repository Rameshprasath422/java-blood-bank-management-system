package blood.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import blood.ExceptionHandler;
import blood.model.DonorDetails;
import blood.view.DisplayDonor;
import blood.DatabaseConnector;

public class DonorMethod extends AbstractUser{
    private static final Logger logger = Logger.getLogger(DonorMethod.class.getName());
    private DisplayDonor displayDonor = new DisplayDonor();
    private ExceptionHandler exceptionHandler = new ExceptionHandler();
    private Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("C:\\Users\\ADMIN\\Desktop\\Online Blood Bank Management\\BloodBankManagementSystem\\src\\blood\\config.properties")) {
            properties.load(input);
        } catch (IOException exception) {
            logger.severe("Error while loading properties: " + exception.getMessage());
        }
        return properties;
    }

    public ArrayList<DonorDetails> getDonorDetailsFromResultSet(ResultSet resultSet) {
        ArrayList<DonorDetails> donorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                DonorDetails donorDetails = new DonorDetails();
                donorDetails.setFirstName(resultSet.getString("FirstName"));
                donorDetails.setLastName(resultSet.getString("LastName"));
                donorDetails.setMobileNumber(resultSet.getString("MobileNumber"));
                donorDetails.setBloodGroup(resultSet.getString("BloodGroup"));
                donorDetails.setUnits(resultSet.getInt("Units"));
                donorList.add(donorDetails);
            }
        } catch (SQLException exception) {
            exceptionHandler.classHandleException(exception);
        }
        return donorList;
    }

    public void displayAndStoreDonorDetails(DonorDetails donorDetails) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = loadProperties();
            properties.getProperty("database.url.details");
            properties.getProperty("database.username");
            properties.getProperty("database.password");
            String insertQuery = properties.getProperty("database.insertQuery");
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, donorDetails.getFirstName());
            preparedStatement.setString(2, donorDetails.getLastName());
            preparedStatement.setString(3, donorDetails.getMobileNumber());
            preparedStatement.setString(4, donorDetails.getBloodGroup());
            if (donorDetails.getUnits() != 0) {
                preparedStatement.setInt(5, donorDetails.getUnits());
            } else {
                preparedStatement.setObject(5, null);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();         
            displayDonorDetails();   
            displayDonor.successMessage();
        } catch (ClassNotFoundException | SQLException exception) {
            exceptionHandler.classHandleException(exception);
        }
    }

    public void displayDonorDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = loadProperties();
            properties.getProperty("database.url.details");
            properties.getProperty("database.username");
            properties.getProperty("database.password");
            String selectQuery = properties.getProperty("database.selectQuery");
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();         
            ArrayList<DonorDetails> donorList = getDonorDetailsFromResultSet(resultSet);
            displayDonor.displayAllDonorDetails(donorList);  
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException exception) {
            exceptionHandler.classHandleException(exception);
        }
    }

}