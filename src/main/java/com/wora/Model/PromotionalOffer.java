package main.java.com.wora.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class PromotionalOffer {

    private UUID id;
    private String offerName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReductionType reductionType;
    private BigDecimal reductionValue;
    private String conditions;
    private OfferStatus offerStatus;

    public PromotionalOffer(UUID id, String offerName, String description,
                            LocalDate startDate, LocalDate endDate,
                            ReductionType reductionType, BigDecimal reductionValue,
                            String conditions, OfferStatus offerStatus) {
        this.id = id;
        this.offerName = offerName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reductionType = reductionType;
        this.reductionValue = reductionValue;
        this.conditions = conditions;
        this.offerStatus = offerStatus;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ReductionType getReductionType() {
        return reductionType;
    }

    public void setReductionType(ReductionType reductionType) {
        this.reductionType = reductionType;
    }

    public BigDecimal getReductionValue() {
        return reductionValue;
    }

    public void setReductionValue(BigDecimal reductionValue) {
        this.reductionValue = reductionValue;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }
}
