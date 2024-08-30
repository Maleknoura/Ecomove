import Model.Partner;
import Service.PartnerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DbFunction db = new DbFunction();
        Connection conn =db.connectToDb("Ecomove","postgres","administrateur");
        PartnerService partnerService = new PartnerService(conn);


        List<Partner> partners = partnerService.getAllPartners();

        if (partners.isEmpty()) {
            System.out.println("Aucun partenaire trouvé.");
        } else {
            for (Partner partner : partners) {
                System.out.println("ID : " + partner.getId());
                System.out.println("Nom de la compagnie : " + partner.getNomCompagnie());
                System.out.println("Contact commercial : " + partner.getContactCommercial());
                System.out.println("Type de transport : " + partner.getTypeTransport());
                System.out.println("Zone géographique : " + partner.getZoneGeographique());
                System.out.println("Conditions spéciales : " + partner.getConditionsSpeciales());
                System.out.println("Statut partenaire : " + partner.getStatutPartenaire());
                System.out.println("Date de création : " + partner.getDateCreation());
                System.out.println("------------------------------");
            }
        }

&
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

