package blood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static Connection connection;
    private static ConfigReader configReader = new ConfigReader();
	public static Connection getConnection()  {
		
		try {
			connection = DriverManager.getConnection(
			        configReader.getDataBaseUrl(),
			        configReader.getDataBaseUsername(),
			        configReader.getDataBasePassword()
			    );
		} catch (SQLException exception) {
			ExceptionHandler.handleException(exception);
		}
	        return connection;
	}
}
