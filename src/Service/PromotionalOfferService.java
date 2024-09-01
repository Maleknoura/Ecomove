package Service;

import DAO.PromotionalOfferDAO;
import Model.PromotionalOffer;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class PromotionalOfferService {
    private PromotionalOfferDAO promotionalOfferDAO;

    public PromotionalOfferService(Connection connection) {
        this.promotionalOfferDAO = new PromotionalOfferDAO(connection);
    }

    public void createPromotionalOffer(PromotionalOffer offer) {
        promotionalOfferDAO.createPromotionalOffer(offer);
    }

    public PromotionalOffer getPromotionalOfferById(UUID id) {
        return promotionalOfferDAO.getPromotionalOfferById(id);
    }

    public void updatePromotionalOffer(PromotionalOffer offer) {
        promotionalOfferDAO.updatePromotionalOffer(offer);
    }

    public void deletePromotionalOffer(UUID id) {
        promotionalOfferDAO.deletePromotionalOffer(id);
    }

    public List<PromotionalOffer> getAllPromotionalOffers() {
        return promotionalOfferDAO.getAllPromotionalOffers();
    }
}
