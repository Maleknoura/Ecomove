package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Contract {

    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal specialRate;
    private String agreementConditions;
    private boolean renewable;
    private ContractStatus contractStatus;
    private UUID partnerId;

    public Contract(UUID id, LocalDate startDate, LocalDate endDate,
                    BigDecimal specialRate, String agreementConditions,
                    boolean renewable, ContractStatus contractStatus, UUID partnerId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialRate = specialRate;
        this.agreementConditions = agreementConditions;
        this.renewable = renewable;
        this.contractStatus = contractStatus;
        this.partnerId = partnerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getSpecialRate() {
        return specialRate;
    }

    public void setSpecialRate(BigDecimal specialRate) {
        this.specialRate = specialRate;
    }

    public String getAgreementConditions() {
        return agreementConditions;
    }

    public void setAgreementConditions(String agreementConditions) {
        this.agreementConditions = agreementConditions;
    }

    public boolean isRenewable() {
        return renewable;
    }

    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }
}
