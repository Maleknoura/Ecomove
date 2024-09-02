package Service;

import DAO.PromotionalOfferDAO;
import Model.PromotionalOffer;
import java.util.List;
import java.util.UUID;

public class PromotionalOfferService {
    private final PromotionalOfferDAO promotionalOfferDAO;

    public PromotionalOfferService(PromotionalOfferDAO promotionalOfferDAO) {
        this.promotionalOfferDAO = promotionalOfferDAO;
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
