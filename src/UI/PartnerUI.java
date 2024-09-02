package UI;


import java.util.Date;
import java.util.UUID;
import java.util.Scanner;
import Model.Partner;
import Model.TransportType;
import Model.PartnerStatus;
import DAO.PartnerDAO;

public class PartnerUI {
    private final PartnerDAO partnerDAO;
    private final Scanner scanner;

    public PartnerUI(PartnerDAO partnerDAO) {
        this.partnerDAO = partnerDAO;
        this.scanner = new Scanner(System.in);
    }

    public void createPartner() {
        System.out.print("Enter company name: ");
        String companyName = scanner.nextLine();

        System.out.print("Enter commercial contact: ");
        String commercialContact = scanner.nextLine();

        System.out.print("Enter transport type (e.g., PLANE, TRAIN, BUS): ");
        TransportType transportType;
        try {
            transportType = TransportType.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid transport type. Using default.");
            transportType = TransportType.PLANE;
        }

        System.out.print("Enter geographic area: ");
        String geographicArea = scanner.nextLine();

        System.out.print("Enter special conditions: ");
        String specialConditions = scanner.nextLine();

        System.out.print("Enter partner status (e.g., ACTIF ,INACTIF, SUSPENDED): ");
        PartnerStatus partnerStatus;
        try {
            partnerStatus = PartnerStatus.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid partner status. Using default.");
            partnerStatus = PartnerStatus.ACTIVE;
        }

        Date creationDate = new Date();


        Partner partner = new Partner(
                UUID.randomUUID(),
                companyName,
                commercialContact,
                transportType,
                geographicArea,
                specialConditions,
                partnerStatus,
                creationDate
        );


        partnerDAO.createPartner(partner);
        System.out.println("Partner created successfully.");
    }

    public void displayPartner(UUID id) {
        Partner partner = partnerDAO.getPartnerById(id);
        if (partner != null) {
            System.out.println("ID: " + partner.getId());
            System.out.println("Company Name: " + partner.getCompanyName());
            System.out.println("Commercial Contact: " + partner.getCommercialContact());
            System.out.println("Transport Type: " + partner.getTransportType());
            System.out.println("Geographic Area: " + partner.getGeographicArea());
            System.out.println("Special Conditions: " + partner.getSpecialConditions());
            System.out.println("Partner Status: " + partner.getPartnerStatus());
            System.out.println("Creation Date: " + partner.getCreationDate());
        } else {
            System.out.println("Partner not found.");
        }
    }

    public void updatePartner() {
        System.out.print("Enter partner ID to update: ");
        UUID id = UUID.fromString(scanner.nextLine());

        Partner partner = partnerDAO.getPartnerById(id);
        if (partner != null) {
            System.out.print("Enter new company name (leave empty to keep current): ");
            String companyName = scanner.nextLine();
            if (!companyName.isEmpty()) partner.setCompanyName(companyName);

            System.out.print("Enter new commercial contact (leave empty to keep current): ");
            String commercialContact = scanner.nextLine();
            if (!commercialContact.isEmpty()) partner.setCommercialContact(commercialContact);

            System.out.print("Enter new transport type (leave empty to keep current): ");
            String transportTypeInput = scanner.nextLine();
            if (!transportTypeInput.isEmpty()) {
                try {
                    partner.setTransportType(TransportType.valueOf(transportTypeInput.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid transport type. Keeping current value.");
                }
            }

            System.out.print("Enter new geographic area (leave empty to keep current): ");
            String geographicArea = scanner.nextLine();
            if (!geographicArea.isEmpty()) partner.setGeographicArea(geographicArea);

            System.out.print("Enter new special conditions (leave empty to keep current): ");
            String specialConditions = scanner.nextLine();
            if (!specialConditions.isEmpty()) partner.setSpecialConditions(specialConditions);

            System.out.print("Enter new partner status (leave empty to keep current): ");
            String partnerStatusInput = scanner.nextLine();
            if (!partnerStatusInput.isEmpty()) {
                try {
                    partner.setPartnerStatus(PartnerStatus.valueOf(partnerStatusInput.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid partner status. Keeping current value.");
                }
            }

            partnerDAO.updatePartner(partner);
            System.out.println("Partner updated successfully.");
        } else {
            System.out.println("Partner not found.");
        }
    }

    public void deletePartner(UUID id) {
        partnerDAO.deletePartner(id);
        System.out.println("Partner deleted successfully.");
    }


    public void listAllPartners() {
        for (Partner partner : partnerDAO.getAllPartners()) {
            System.out.println("ID: " + partner.getId());
            System.out.println("Company Name: " + partner.getCompanyName());
            System.out.println("Commercial Contact: " + partner.getCommercialContact());
            System.out.println("Transport Type: " + partner.getTransportType());
            System.out.println("Geographic Area: " + partner.getGeographicArea());
            System.out.println("Special Conditions: " + partner.getSpecialConditions());
            System.out.println("Partner Status: " + partner.getPartnerStatus());
            System.out.println("Creation Date: " + partner.getCreationDate());
            System.out.println("---------------------------------------");
        }
    }
}
