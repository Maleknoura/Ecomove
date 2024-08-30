package Service;
import DAO.PartnerDAO;
import Model.Partner;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class PartnerService {
    private PartnerDAO partnerDAO;

    public PartnerService(Connection connection) {
        this.partnerDAO = new PartnerDAO(connection);
    }

    public void createPartner(Partner partner) {
        partnerDAO.createPartner(partner);
    }

    public Partner getPartnerById(UUID id) {
        return partnerDAO.getPartnerById(id);
    }

    public void updatePartner(Partner partner) {
        partnerDAO.updatePartner(partner);
    }

    public void deletePartner(UUID id) {
        partnerDAO.deletePartner(id);
    }
    public List<Partner> getAllPartners() {
        return partnerDAO.getAllPartners();
    }
}

