package blood;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;
public class ConfigReader {
	private static final Logger logger = Logger.getLogger(ConfigReader.class.getName());
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("C:\\Users\\ADMIN\\Desktop\\Online Blood Bank Management System\\BloodBankManagement\\src\\blood\\config.properties")) 
        {
            properties.load(input);
        } catch (IOException exception) {
        	logger.severe("Error while loading properties: " + exception.getMessage());
        }
    } 

    public String getDataBaseUrl() {
        return properties.getProperty("database.url");
    }

    public String getDataBaseUsername() {
        return properties.getProperty("database.username");
    }

    public String getDataBasePassword() {
        return properties.getProperty("database.password");
    }


	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
