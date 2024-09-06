package main.java.com.wora.UI;

import main.java.com.wora.DAO.PromotionalOfferDAO;
import main.java.com.wora.Model.OfferStatus;
import main.java.com.wora.Model.PromotionalOffer;
import main.java.com.wora.Model.ReductionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PromotionalOfferUI {
    private final PromotionalOfferDAO promotionalOfferDAO;
    private final Scanner scanner;

    public PromotionalOfferUI(PromotionalOfferDAO promotionalOfferDAO) {
        this.promotionalOfferDAO = promotionalOfferDAO;
        this.scanner = new Scanner(System.in);
    }

    public void createPromotionalOffer() {
        System.out.print("Enter offer name: ");
        String offerName = scanner.nextLine();

        System.out.print("Enter offer description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start date (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter end date (yyyy-mm-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter discount type (PERCENTAGE/FIXED_AMOUNT): ");
        String discountTypeInput = scanner.nextLine();
        ReductionType reductionType = ReductionType.valueOf(discountTypeInput.toUpperCase());

        System.out.print("Enter discount value: ");
        BigDecimal reductionValue = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Enter conditions: ");
        String conditions = scanner.nextLine();

        PromotionalOffer offer = new PromotionalOffer(
                UUID.randomUUID(),
                offerName,
                description,
                startDate,
                endDate,
                reductionType,
                reductionValue,
                conditions,
                OfferStatus.ACTIVE
        );

        promotionalOfferDAO.createPromotionalOffer(offer);
        System.out.println("Promotional offer created successfully.");
    }

    public void displayPromotionalOffer(UUID id) {
        PromotionalOffer offer = promotionalOfferDAO.getPromotionalOfferById(id);
        if (offer != null) {
            System.out.println(offer);
        } else {
            System.out.println("Offer not found.");
        }
    }

    public void updatePromotionalOffer(UUID id) {
        PromotionalOffer offer = promotionalOfferDAO.getPromotionalOfferById(id);
        if (offer != null) {
            System.out.print("Enter new offer name: ");
            offer.setOfferName(scanner.nextLine());

            System.out.print("Enter new offer description: ");
            offer.setDescription(scanner.nextLine());

            System.out.print("Enter start date (yyyy-mm-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter end date (yyyy-mm-dd): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());


            System.out.print("Enter new discount type (PERCENTAGE/FIXED_AMOUNT): ");
            offer.setReductionType(ReductionType.valueOf(scanner.nextLine().toUpperCase()));

            System.out.print("Enter new discount value: ");
            offer.setReductionValue(scanner.nextBigDecimal());
            scanner.nextLine();

            System.out.print("Enter new conditions: ");
            offer.setConditions(scanner.nextLine());

            promotionalOfferDAO.updatePromotionalOffer(offer);
            System.out.println("Promotional offer updated successfully.");
        } else {
            System.out.println("Offer not found.");
        }
    }

    public void deletePromotionalOffer(UUID id) {
        promotionalOfferDAO.deletePromotionalOffer(id);
        System.out.println("Promotional offer deleted successfully.");
    }

    public void listAllPromotionalOffers() {
        List<PromotionalOffer> offers = promotionalOfferDAO.getAllPromotionalOffers();
        if (offers.isEmpty()) {
            System.out.println("No offers found.");
        } else {
            for (PromotionalOffer offer : offers) {
                System.out.println(offer);
            }
        }
    }
}
