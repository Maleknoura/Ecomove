CREATE TYPE TransportType AS ENUM ('PLANE', 'TRAIN', 'BUS');
CREATE TYPE PartnerStatus AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');
CREATE TYPE ContractStatus AS ENUM ('ONGOING', 'FINISHED', 'SUSPENDED');
CREATE TYPE TicketStatus AS ENUM ('SOLD', 'CANCELLED', 'PENDING');
CREATE TYPE ReductionType AS ENUM ('PERCENTAGE', 'FIXED_AMOUNT');
CREATE TYPE OfferStatus AS ENUM ('ACTIVE', 'EXPIRED', 'SUSPENDED');


CREATE TABLE Partner (
    id UUID PRIMARY KEY,
    companyName VARCHAR(255) NOT NULL,
    commercialContact TEXT,
    transportType TransportType,
    geographicZone VARCHAR(255),
    specialConditions TEXT,
    partnerStatus PartnerStatus,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Contract (
    id UUID PRIMARY KEY,
    startDate DATE NOT NULL,
    endDate DATE,
    specialRate DECIMAL,
    agreementConditions TEXT,
    renewable BOOLEAN,
    contractStatus ContractStatus,
    partnerId UUID,
    FOREIGN KEY (partnerId) REFERENCES Partner(id) ON DELETE CASCADE
);

CREATE TABLE Ticket (
    id UUID PRIMARY KEY,
    transportType TransportType,
    purchasePrice DECIMAL,
    salePrice DECIMAL,
    saleDate TIMESTAMP,
    ticketStatus TicketStatus,
    contractId UUID,
    FOREIGN KEY (contractId) REFERENCES Contract(id) ON DELETE SET NULL
);

CREATE TABLE PromotionalOffer (
    id UUID PRIMARY KEY,
    offerName VARCHAR(255) NOT NULL,
    description TEXT,
    startDate DATE,
    endDate DATE,
    reductionType ReductionType,
    reductionValue DECIMAL,
    conditions TEXT,
    offerStatus OfferStatus
);
