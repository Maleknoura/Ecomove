package Model;

import java.util.Date;
import java.util.UUID;

public class Partner {

    private UUID id;
    private String companyName;
    private String commercialContact;
    private TransportType transportType;
    private String geographicArea;
    private String specialConditions;
    private PartnerStatus partnerStatus;
    private Date creationDate;

    public Partner(UUID id, String companyName, String commercialContact,
                   TransportType transportType, String geographicArea,
                   String specialConditions, PartnerStatus partnerStatus,
                   Date creationDate) {
        this.id = id;
        this.companyName = companyName;
        this.commercialContact = commercialContact;
        this.transportType = transportType;
        this.geographicArea = geographicArea;
        this.specialConditions = specialConditions;
        this.partnerStatus = partnerStatus;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCommercialContact() {
        return commercialContact;
    }

    public void setCommercialContact(String commercialContact) {
        this.commercialContact = commercialContact;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getGeographicArea() {
        return geographicArea;
    }

    public void setGeographicArea(String geographicArea) {
        this.geographicArea = geographicArea;
    }

    public String getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    public PartnerStatus getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(PartnerStatus partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
