package blood.view;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import blood.model.DonorDetails;

public class DisplayDonor {
    private static final Logger logger = Logger.getLogger(DisplayDonor.class.getName());
    private Scanner scanner = new Scanner(System.in);

    public void getDonorDetails(DonorDetails donorDetails) {
        System.out.println("Enter Donor Details:");
        System.out.println("Enter First Name: ");
        donorDetails.setFirstName(scanner.next());
        System.out.println("Enter Last Name: ");
        donorDetails.setLastName(scanner.next());
        System.out.println("Enter Mobile Number: ");
        donorDetails.setMobileNumber(scanner.next());
        System.out.println("Enter Blood Group: ");
        donorDetails.setBloodGroup(scanner.next());
        System.out.println("Enter Number of Units: ");
        donorDetails.setUnits(scanner.nextInt());
    }

    public void displayDonorDetails(DonorDetails donorDetails) {
        System.out.println("Donor Details:");
        System.out.println("Name: " + donorDetails.getFirstName() + " " + donorDetails.getLastName());
        System.out.println("Mobile Number: " + donorDetails.getMobileNumber());
        System.out.println("Blood Group: " + donorDetails.getBloodGroup());
        System.out.println("Number of Units: " + donorDetails.getUnits());
    }

    public void displayAllDonorDetails(ArrayList<DonorDetails> donorList) {
        for (DonorDetails donorDetails : donorList) {
            displayDonorDetails(donorDetails);
            System.out.println();
            logger.info("Donor Details Displayed");
        }
    }

    public void successMessage() {
        logger.info("Donor details successfully stored in the database.");
    }
}
