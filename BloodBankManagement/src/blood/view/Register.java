package blood.view;
import java.util.Scanner;
import java.util.logging.Logger;

import blood.model.UserDetails;
public class Register{
	UserDetails userDetails=new UserDetails();
	private Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    public int readAmount() {
        System.out.println("Enter amount of blood required:");
        return scanner.nextInt();
    }

    public String bloodGroup() {
        System.out.println("Enter Blood Group and Type (e.g., APositive, ONegative):");
        String bloodGroupType = scanner.next().toUpperCase();
        return bloodGroupType;
    }

    public void handleResult() {
        logger.info("Your Request is Done");
    }

    public void handleOutOfStockMessage(int availableUnits, int remainingUnits) {
        System.out.println("Your requested blood group is out of stock");
        logger.info("Thank you for visiting Welcome Again!");
    }

    public void register() {
        System.out.println("Enter Patient Details");
        System.out.println("Enter First name:");
        userDetails.setFirstName(scanner.nextLine());
        System.out.println("Enter Last name:");
        userDetails. setLastName(scanner.nextLine());
        System.out.println("Enter Age:");
        userDetails.setAge(scanner.nextInt());
        System.out.println("Enter Gender:");
        userDetails.setGender(scanner.next());
    }
    public void handleOutOfStockMessage(int availableUnits) {
        System.out.println(availableUnits + " units are given and remaining units are out of stock");
        logger.info("Thank you for visiting.");
    }
    public void handleInvalidBloodGroup() {
        logger.info("Invalid Blood Group");
    }
}
