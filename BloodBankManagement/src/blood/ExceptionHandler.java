package blood;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.lang.Exception;
public class ExceptionHandler {
	private static final Logger logger = Logger.getLogger(ExceptionHandler.class.getName());
    public static void handleException(Exception exception) {
        if (exception instanceof SQLException) {
            handleSQLException((SQLException) exception);
        } else {
        	logger.severe("An error occurred: " + exception.getMessage());
        }
    }

    private static void handleSQLException(SQLException exception) {
        if (exception.getMessage().contains("Access denied for user")) {
        	logger.severe("Access denied. Please check your SQL username and password.");
        } else {
        	logger.severe("Database error: " + exception.getMessage());
        }
        
    }
  
	public void classHandleException(Exception exception) {
		if (exception instanceof SQLException) {
            if (exception.getMessage().contains("Access denied for user")) {
            	logger.severe("Access denied. Please check your SQL username and password.");
            } else {
            	logger.severe("Database error: " + exception.getMessage());
            }
        } else if (exception instanceof ClassNotFoundException) {
        	logger.severe("JDBC driver not found: " + exception.getMessage());
        } else {
        	logger.severe("An error occurred: " + exception.getMessage());
        }
		
	}
}
