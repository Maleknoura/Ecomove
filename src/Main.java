import DAO.ContractDAO;
import DAO.PartnerDAO;
import DataBase.DbFunction;
import DAO.PromotionalOfferDAO;
import Service.ContractService;
import Service.TicketService;
import UI.*;


import java.sql.Connection;
import DAO.TicketDAO;

public class Main {
    public static void main(String[] args) {

        DbFunction db = DbFunction.getInstance();
        Connection conn = db.connectToDb("Ecomove", "postgres", "administrateur");
        PartnerDAO partnerDAO = new PartnerDAO(conn);
        ContractDAO contractDAO = new ContractDAO(conn);
        PromotionalOfferDAO promotionalOfferDAO = new PromotionalOfferDAO(conn);
        TicketDAO ticketDAO = new TicketDAO(conn);


        ContractService contractService = new ContractService(contractDAO);
        TicketService ticketService = new TicketService(ticketDAO);


        PartnerUI partnerUI = new PartnerUI(partnerDAO);
        ContractUI contractUI = new ContractUI(contractService);
        PromotionalOfferUI promotionalOfferUI = new PromotionalOfferUI(promotionalOfferDAO);
        TicketUI ticketUI = new TicketUI(ticketService);

        ConsoleUI consoleUI = new ConsoleUI(conn);

        consoleUI.showMainMenu();

    }
}
