package blood.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Logger;
import blood.ExceptionHandler;
import blood.view.Register;
import blood.DatabaseConnector;

public class BloodUnit {
    private static final Logger logger = Logger.getLogger(BloodUnit.class.getName());
    Register register = new Register();

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("C:\\Users\\ADMIN\\Desktop\\Online Blood Bank Management System\\BloodBankManagement\\src\\blood\\config.properties")) {
            properties.load(input);
        } catch (IOException exception) {
            logger.severe("Error while loading properties: " + exception.getMessage());
        }
        return properties;
    }

    public void unit() {
        try {
            int unitsToRequest = register.readAmount();
            String bloodGroupType = register.bloodGroup();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = loadProperties();
            properties.getProperty("database.url.units");
            properties.getProperty("database.username");
            properties.getProperty("database.password");
            try (Connection connection = DatabaseConnector.getConnection()) {
                String selectQuery = properties.getProperty("database.selectedQuery");
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                selectStatement.setString(1, bloodGroupType);
                ResultSet resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    int availableUnits = resultSet.getInt("Units");
                    if (unitsToRequest == availableUnits) {
                        handleResult();
                    } else if (unitsToRequest < availableUnits) {
                        int remainingUnits = availableUnits - unitsToRequest;
                        String updateQuery = properties.getProperty("database.updateQuery");
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setInt(1, remainingUnits);
                        updateStatement.setString(2, bloodGroupType);
                        updateStatement.executeUpdate();
                        updateStatement.close();
                        handleResult();
                    } else {
                        String updateQuery = properties.getProperty("database.updatedQuery");
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setString(1, bloodGroupType);
                        updateStatement.executeUpdate();
                        updateStatement.close();

                        if (availableUnits > 0) {
                            handleOutOfStockMessage(availableUnits);
                        } else {
                            handleOutOfStockMessage(availableUnits, 0);
                        }
                    }
                } else {
                    register.handleInvalidBloodGroup();
                }
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    private void handleResult() {
        register.handleResult();
    }

    private void handleOutOfStockMessage(int availableUnits) {
        register.handleOutOfStockMessage(availableUnits);
    }

    private void handleOutOfStockMessage(int availableUnits, int remainingUnits) {
        register.handleOutOfStockMessage(availableUnits, remainingUnits);
    }

    private void handleException(Exception exception) {
        ExceptionHandler.handleException(exception);
    }
}
