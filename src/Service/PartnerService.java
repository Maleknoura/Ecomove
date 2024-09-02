package Service;
import DAO.PartnerDAO;
import Model.Partner;

import java.util.List;
import java.util.UUID;

public class PartnerService {
    private final PartnerDAO partnerDAO;
    public PartnerService(PartnerDAO partnerDAO) {
        this.partnerDAO = partnerDAO;
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

