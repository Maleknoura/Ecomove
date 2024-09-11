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
('550e8400-e29b-41d4-a716-446655440000', 'Marrakech', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440001', 'Marrakech', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440002', 'Marrakech', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440003', 'Marrakech', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440004', 'Casablanca', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440005', 'Casablanca', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440006', 'Casablanca', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440007', 'Tanger', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440008', 'Tanger', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440009', 'Safi', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440010', 'Rabat', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440011', 'Rabat', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440012', 'Rabat', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440013', 'Rabat', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440014', 'Rabat', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440015', 'Meknès', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440016', 'Meknès', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440017', 'Meknès', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440018', 'Meknès', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440019', 'Meknès', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440020', 'Oujda', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440021', 'Oujda', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440022', 'Oujda', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440023', 'Oujda', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440024', 'Oujda', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440025', 'El Jadida', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440026', 'El Jadida', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440027', 'El Jadida', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440028', 'El Jadida', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440029', 'El Jadida', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440030', 'Nador', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440031', 'Nador', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440032', 'Nador', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440033', 'Nador', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440034', 'Nador', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440035', 'Kénitra', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440036', 'Kénitra', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440037', 'Kénitra', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440038', 'Kénitra', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440039', 'Kénitra', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440040', 'Tétouan', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440041', 'Tétouan', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440042', 'Tétouan', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440043', 'Tétouan', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440044', 'Tétouan', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440045', 'Beni Mellal', 'Casablanca', NULL),
('550e8400-e29b-41d4-a716-446655440046', 'Beni Mellal', 'Marrakech', NULL),
('550e8400-e29b-41d4-a716-446655440047', 'Beni Mellal', 'Tanger', NULL),
('550e8400-e29b-41d4-a716-446655440048', 'Beni Mellal', 'Safi', NULL),
('550e8400-e29b-41d4-a716-446655440049', 'Beni Mellal', 'Agadir', NULL),
('550e8400-e29b-41d4-a716-446655440050', 'Khouribga', 'Casablanca', NULL);
