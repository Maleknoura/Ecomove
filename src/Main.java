import DataBase.DbFunction;
import Service.PartnerService;
import DAO.PartnerDAO;
import Model.Partner;
import UI.PartnerUI;

import java.sql.Connection;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        DbFunction db = new DbFunction();
        Connection conn = db.connectToDb("Ecomove", "postgres", "administrateur");

        // Initialisation de PartnerDAO avec la connexion
        PartnerDAO partnerDAO = new PartnerDAO(conn);

        // Initialisation de PartnerService avec PartnerDAO
        PartnerService partnerService = new PartnerService(partnerDAO);

        // Création de l'interface utilisateur pour les partenaires
        PartnerUI partnerUI = new PartnerUI(partnerDAO);

        // Exemple d'appel des méthodes CRUD
        // Créer un nouveau partenaire
        partnerUI.createPartner();

        // Afficher un partenaire (vous devez fournir un ID valide ici)
        // Remplacez UUID.randomUUID() par l'ID du partenaire que vous souhaitez afficher
        UUID idToDisplay = UUID.randomUUID(); // Remplacez par un ID valide
        partnerUI.displayPartner(idToDisplay);

        // Mettre à jour un partenaire (vous devez fournir un ID valide ici)
        // Remplacez UUID.randomUUID() par l'ID du partenaire que vous souhaitez mettre à jour
        UUID idToUpdate = UUID.randomUUID(); // Remplacez par un ID valide
        partnerUI.updatePartner();

        // Supprimer un partenaire (vous devez fournir un ID valide ici)
        // Remplacez UUID.randomUUID() par l'ID du partenaire que vous souhaitez supprimer
        UUID idToDelete = UUID.randomUUID(); // Remplacez par un ID valide
        partnerUI.deletePartner();

        // Lister tous les partenaires
        partnerUI.listAllPartners();
    }
}
