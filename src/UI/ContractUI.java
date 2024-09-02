package UI;

import DAO.ContractDAO;
import Model.Contract;
import Model.ContractStatus;
import Service.ContractService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class ContractUI {

    private final ContractService contractService;
    private final Scanner scanner;

    public ContractUI(ContractService contractService) {
        this.contractService = contractService;
        this.scanner = new Scanner(System.in);
    }

    public void createContract() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter end date (YYYY-MM-DD or leave empty if none): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);

        System.out.print("Enter special rate: ");
        BigDecimal specialRate = new BigDecimal(scanner.nextLine());

        System.out.print("Enter agreement conditions: ");
        String agreementConditions = scanner.nextLine();

        System.out.print("Is renewable (true/false): ");
        boolean renewable = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter contract status (e.g., ACTIVE, TERMINATED, SUSPENDED): ");
        ContractStatus contractStatus;
        try {
            contractStatus = ContractStatus.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid contract status. Using default (ONGOING).");
            contractStatus = ContractStatus.ONGOING;
        }

        System.out.print("Enter partner ID (UUID): ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        Contract contract = new Contract(
                UUID.randomUUID(),
                startDate,
                endDate,
                specialRate,
                agreementConditions,
                renewable,
                contractStatus,
                partnerId
        );

        contractService.createContract(contract);
        System.out.println("Contract created successfully.");
    }

    public void displayContract(UUID id) {
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            System.out.println("ID: " + contract.getId());
            System.out.println("Start Date: " + contract.getStartDate());
            System.out.println("End Date: " + (contract.getEndDate() != null ? contract.getEndDate() : "N/A"));
            System.out.println("Special Rate: " + contract.getSpecialRate());
            System.out.println("Agreement Conditions: " + contract.getAgreementConditions());
            System.out.println("Renewable: " + contract.isRenewable());
            System.out.println("Contract Status: " + contract.getContractStatus());
            System.out.println("Partner ID: " + contract.getPartnerId());
        } else {
            System.out.println("Contract not found.");
        }
    }

    public void updateContract() {
        System.out.print("Enter contract ID to update: ");
        UUID id = UUID.fromString(scanner.nextLine());

        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            System.out.print("Enter new start date (YYYY-MM-DD or leave empty to keep current): ");
            String startDateInput = scanner.nextLine();
            if (!startDateInput.isEmpty()) {
                contract.setStartDate(LocalDate.parse(startDateInput));
            }

            System.out.print("Enter new end date (YYYY-MM-DD or leave empty to keep current): ");
            String endDateInput = scanner.nextLine();
            if (!endDateInput.isEmpty()) {
                contract.setEndDate(LocalDate.parse(endDateInput));
            }

            System.out.print("Enter new special rate (or leave empty to keep current): ");
            String specialRateInput = scanner.nextLine();
            if (!specialRateInput.isEmpty()) {
                contract.setSpecialRate(new BigDecimal(specialRateInput));
            }

            System.out.print("Enter new agreement conditions (or leave empty to keep current): ");
            String agreementConditions = scanner.nextLine();
            if (!agreementConditions.isEmpty()) {
                contract.setAgreementConditions(agreementConditions);
            }

            System.out.print("Is renewable (true/false or leave empty to keep current): ");
            String renewableInput = scanner.nextLine();
            if (!renewableInput.isEmpty()) {
                contract.setRenewable(Boolean.parseBoolean(renewableInput));
            }

            System.out.print("Enter new contract status (or leave empty to keep current): ");
            String contractStatusInput = scanner.nextLine();
            if (!contractStatusInput.isEmpty()) {
                try {
                    contract.setContractStatus(ContractStatus.valueOf(contractStatusInput.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid contract status. Keeping current value.");
                }
            }

            contractService.updateContract(contract);
            System.out.println("Contract updated successfully.");
        } else {
            System.out.println("Contract not found.");
        }
    }


    public void deleteContract(UUID id) {
        contractService.deleteContract(id);
        System.out.println("Contract deleted successfully.");
    }

    public void listAllContracts() {
        for (Contract contract : contractService.getAllContracts()) {
            System.out.println("ID: " + contract.getId());
            System.out.println("Start Date: " + contract.getStartDate());
            System.out.println("End Date: " + (contract.getEndDate() != null ? contract.getEndDate() : "N/A"));
            System.out.println("Special Rate: " + contract.getSpecialRate());
            System.out.println("Agreement Conditions: " + contract.getAgreementConditions());
            System.out.println("Renewable: " + contract.isRenewable());
            System.out.println("Contract Status: " + contract.getContractStatus());
            System.out.println("Partner ID: " + contract.getPartnerId());
            System.out.println("---------------------------------------");
        }
    }
}
