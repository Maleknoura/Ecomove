import DataBase.DbFunction;
import Service.PartnerService;
import DAO.PartnerDAO;
import Model.Partner;
import UI.PartnerUI;

import java.sql.Connection;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        DbFunction db = DbFunction.getInstance();
        Connection conn = db.connectToDb("Ecomove", "postgres", "administrateur");

        PartnerDAO partnerDAO = new PartnerDAO(conn);

        PartnerService partnerService = new PartnerService(partnerDAO);

        PartnerUI partnerUI = new PartnerUI(partnerDAO);


        partnerUI.createPartner();







        partnerUI.listAllPartners();
    }
}
