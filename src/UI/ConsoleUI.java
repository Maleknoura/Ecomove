package UI;

import DAO.*;
import Service.*;
import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {

    private PartnerDAO partnerDAO;
    private ContractDAO contractDAO;
    private PromotionalOfferDAO promotionalOfferDAO;
    private TicketDAO ticketDAO;

    private PartnerUI partnerUI;
    private ContractUI contractUI;
    private PromotionalOfferUI promotionalOfferUI;
    private TicketUI ticketUI;

    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Connection conn) {
        this.partnerDAO = new PartnerDAO(conn);
        this.contractDAO = new ContractDAO(conn);
        this.promotionalOfferDAO = new PromotionalOfferDAO(conn);
        this.ticketDAO = new TicketDAO(conn);


        ContractService contractService = new ContractService(contractDAO);
        TicketService ticketService = new TicketService(ticketDAO);


        this.partnerUI = new PartnerUI(partnerDAO);
        this.contractUI = new ContractUI(contractService);
        this.promotionalOfferUI = new PromotionalOfferUI(promotionalOfferDAO);
        this.ticketUI = new TicketUI(ticketService);
    }

    public void showMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Manage Partners");
            System.out.println("2. Manage Contracts");
            System.out.println("3. Manage Promotional Offers");
            System.out.println("4. Manage Tickets");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showPartnerMenu();
                    break;
                case 2:
                    showContractMenu();
                    break;
                case 3:
                    showPromotionalOfferMenu();
                    break;
                case 4:
                    showTicketMenu();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showPartnerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Partner Management Menu:");
            System.out.println("1. Create Partner");
            System.out.println("2. Display Partner");
            System.out.println("3. Update Partner");
            System.out.println("4. Delete Partner");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    partnerUI.createPartner();
                    break;
                case 2:
                    partnerUI.listAllPartners();
                    break;
                case 3:
                    partnerUI.updatePartner();
                    break;
                case 4:
                    System.out.print("Enter partner ID to delete: ");
                    UUID deleteId = UUID.fromString(scanner.nextLine());
                    partnerUI.deletePartner(deleteId);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showContractMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Contract Management Menu:");
            System.out.println("1. Add Contract");
            System.out.println("2. Display Contract");
            System.out.println("3. Update Contract");
            System.out.println("4. Delete Contract");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    contractUI.createContract();
                    break;
                case 2:
                    contractUI.listAllContracts();
                    break;
                case 3:
                    contractUI.updateContract();
                    break;
                case 4:
                    System.out.print("Enter contract ID to delete: ");
                    UUID deleteId = UUID.fromString(scanner.nextLine());
                    contractUI.deleteContract(deleteId);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showPromotionalOfferMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Promotional Offer Management Menu:");
            System.out.println("1. Create Promotional Offer");
            System.out.println("2. Display Promotional Offer");
            System.out.println("3. Update Promotional Offer");
            System.out.println("4. Delete Promotional Offer");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    promotionalOfferUI.createPromotionalOffer();
                    break;
                case 2:
                    promotionalOfferUI.listAllPromotionalOffers();
                    break;
                case 3:
                    System.out.print("Enter promotional offer ID to update: ");
                    UUID updateId = UUID.fromString(scanner.nextLine());
                    promotionalOfferUI.updatePromotionalOffer(updateId);
                    break;
                case 4:
                    System.out.print("Enter promotional offer ID to delete: ");
                    UUID deleteId = UUID.fromString(scanner.nextLine());
                    promotionalOfferUI.deletePromotionalOffer(deleteId);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showTicketMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Ticket Management Menu:");
            System.out.println("1. Create Ticket");
            System.out.println("2. Display Ticket");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ticketUI.createTicket();
                    break;
                case 2:
                    ticketUI.listAllTickets();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
