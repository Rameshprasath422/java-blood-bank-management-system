package blood.view;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuChoice {
	private static final Logger logger=Logger.getLogger(MenuChoice.class.getName());
    private static Scanner scanner = new Scanner(System.in);
   
	public void getMenuChoice() {
		System.out.println("Welcome to Blood Bank Management System");
        System.out.println("1.Admin");
        System.out.println("2.User");
        System.out.println("3.Exit");
        System.out.println("Enter your choice");
		
	}

	public void exitMessage() {
		logger.info("Exiting program");
        logger.info("Thank you for visiting Welcome Again!");
		
	}

	public void invalidChoice() {
		logger.info("Invalid choice");	
		
	}

	public String getAdminUsername() {
		System.out.println("Enter Admin Username: ");
        return scanner.next();
	}

	public String getAdminPassword() {
		 System.out.println("Enter Admin Password: ");
	        return scanner.next();
	}

	public void displayAdminLoginSuccess() {
		   logger.info("Admin Login Success");
		
	}

	public void displayAdminLoginInvalid() {
		 logger.info("Invalid Credentials for Admin Login");
		
	}
}
