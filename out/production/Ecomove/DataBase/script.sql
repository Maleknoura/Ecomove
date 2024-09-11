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
    saleDate DATE,
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
CREATE TABLE Client (
      Email VARCHAR(255) UNIQUE PRIMARY KEY,
      FirstName VARCHAR(255),
      LastName VARCHAR(255),
      NumberPhone int
  );
CREATE TABLE Station (
    id UUID PRIMARY KEY,
    departureLocation VARCHAR(100),
    arrivalLocation VARCHAR(100),
    distance DOUBLE
);

INSERT INTO Station (id, departureLocation, arrivalLocation, distance) VALUES
('1', 'Marrakech', 'Casablanca', 243.0),
('2', 'Marrakech', 'Tanger', 571.0),
('3', 'Marrakech', 'Safi', 278.0),
('4', 'Marrakech', 'Agadir', 233.0),

('5', 'Casablanca', 'Tanger', 340.0),
('6', 'Casablanca', 'Safi', 286.0),
('7', 'Casablanca', 'Agadir', 446.0),

('8', 'Tanger', 'Safi', 778.0),
('9', 'Tanger', 'Agadir', 746.0),

('10', 'Safi', 'Agadir', 167.0);