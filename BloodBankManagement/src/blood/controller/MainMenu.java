package blood.controller;
import java.util.Scanner;

import blood.model.DonorDetails;
import blood.view.DisplayDonor;
import blood.view.MenuChoice;
import blood.view.Register;
public class MainMenu {
    private DonorDetails donorDetails = new DonorDetails();
    private DonorMethod donorMethod = new DonorMethod();
    private DisplayDonor displayDonor = new DisplayDonor();
    private MenuChoice menuChoice = new MenuChoice();
    public void start() {
        Scanner scanner = new Scanner(System.in);
        menuChoice.getMenuChoice();
        int choice = scanner.nextInt();
        if (choice == 1) {
            adminMenu();
            start();
        } else if (choice == 2) {
            userMenu();
            start();
        } else if (choice == 3) {
            menuChoice.exitMessage();
            scanner.close();
        } else {
            menuChoice.invalidChoice();
            start();
        }
    }

    private void adminMenu() {
        String adminUsername = menuChoice.getAdminUsername();
        String adminPassword = menuChoice.getAdminPassword();
        if (AdminAuthentication.authenticateAdmin(adminUsername, adminPassword)) {
            menuChoice.displayAdminLoginSuccess();
            displayDonor.getDonorDetails(donorDetails);
            donorMethod.displayAndStoreDonorDetails(donorDetails);
        } 
        else 
        {
            menuChoice.displayAdminLoginInvalid();
        }
    }
    
    private void userMenu() {
        Register register = new Register();
        register.register();
        BloodUnit bloodUnit = new BloodUnit();
        bloodUnit.unit();
    }
}

