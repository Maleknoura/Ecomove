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
('550e8400-e29b-41d4-a716-446655440000', 'Marrakech', 'Casablanca', 244),
('550e8400-e29b-41d4-a716-446655440001', 'Marrakech', 'Tanger', 575),
('550e8400-e29b-41d4-a716-446655440002', 'Marrakech', 'Safi', 157),
('550e8400-e29b-41d4-a716-446655440003', 'Marrakech', 'Agadir', 258),
('550e8400-e29b-41d4-a716-446655440004', 'Casablanca', 'Tanger', 338),
('550e8400-e29b-41d4-a716-446655440005', 'Casablanca', 'Safi', 242),
('550e8400-e29b-41d4-a716-446655440006', 'Casablanca', 'Agadir', 460),
('550e8400-e29b-41d4-a716-446655440007', 'Tanger', 'Safi', 650),
('550e8400-e29b-41d4-a716-446655440008', 'Tanger', 'Agadir', 850),
('550e8400-e29b-41d4-a716-446655440009', 'Safi', 'Agadir', 329),
('550e8400-e29b-41d4-a716-446655440010', 'Rabat', 'Casablanca', 87),
('550e8400-e29b-41d4-a716-446655440011', 'Rabat', 'Marrakech', 332),
('550e8400-e29b-41d4-a716-446655440012', 'Rabat', 'Tanger', 286),
('550e8400-e29b-41d4-a716-446655440013', 'Rabat', 'Safi', 352),
('550e8400-e29b-41d4-a716-446655440014', 'Rabat', 'Agadir', 544),
('550e8400-e29b-41d4-a716-446655440015', 'Meknès', 'Casablanca', 240),
('550e8400-e29b-41d4-a716-446655440016', 'Meknès', 'Marrakech', 480),
('550e8400-e29b-41d4-a716-446655440017', 'Meknès', 'Tanger', 370),
('550e8400-e29b-41d4-a716-446655440018', 'Meknès', 'Safi', 470),
('550e8400-e29b-41d4-a716-446655440019', 'Meknès', 'Agadir', 660),
('550e8400-e29b-41d4-a716-446655440020', 'Oujda', 'Casablanca', 625),
('550e8400-e29b-41d4-a716-446655440021', 'Oujda', 'Marrakech', 790),
('550e8400-e29b-41d4-a716-446655440022', 'Oujda', 'Tanger', 580),
('550e8400-e29b-41d4-a716-446655440023', 'Oujda', 'Safi', 810),
('550e8400-e29b-41d4-a716-446655440024', 'Oujda', 'Agadir', 990),
('550e8400-e29b-41d4-a716-446655440025', 'El Jadida', 'Casablanca', 105),
('550e8400-e29b-41d4-a716-446655440026', 'El Jadida', 'Marrakech', 191),
('550e8400-e29b-41d4-a716-446655440027', 'El Jadida', 'Tanger', 490),
('550e8400-e29b-41d4-a716-446655440028', 'El Jadida', 'Safi', 150),
('550e8400-e29b-41d4-a716-446655440029', 'El Jadida', 'Agadir', 360),
('550e8400-e29b-41d4-a716-446655440030', 'Nador', 'Casablanca', 620),
('550e8400-e29b-41d4-a716-446655440031', 'Nador', 'Marrakech', 820),
('550e8400-e29b-41d4-a716-446655440032', 'Nador', 'Tanger', 680),
('550e8400-e29b-41d4-a716-446655440033', 'Nador', 'Safi', 750),
('550e8400-e29b-41d4-a716-446655440034', 'Nador', 'Agadir', 940),
('550e8400-e29b-41d4-a716-446655440035', 'Kénitra', 'Casablanca', 127),
('550e8400-e29b-41d4-a716-446655440036', 'Kénitra', 'Marrakech', 320),
('550e8400-e29b-41d4-a716-446655440037', 'Kénitra', 'Tanger', 230),
('550e8400-e29b-41d4-a716-446655440038', 'Kénitra', 'Safi', 357),
('550e8400-e29b-41d4-a716-446655440039', 'Kénitra', 'Agadir', 540),
('550e8400-e29b-41d4-a716-446655440040', 'Tétouan', 'Casablanca', 366),
('550e8400-e29b-41d4-a716-446655440041', 'Tétouan', 'Marrakech', 615),
('550e8400-e29b-41d4-a716-446655440042', 'Tétouan', 'Tanger', 61),
('550e8400-e29b-41d4-a716-446655440043', 'Tétouan', 'Safi', 555),
('550e8400-e29b-41d4-a716-446655440044', 'Tétouan', 'Agadir', 745),
('550e8400-e29b-41d4-a716-446655440045', 'Beni Mellal', 'Casablanca', 210),
('550e8400-e29b-41d4-a716-446655440046', 'Beni Mellal', 'Marrakech', 191),
('550e8400-e29b-41d4-a716-446655440047', 'Beni Mellal', 'Tanger', 560),
('550e8400-e29b-41d4-a716-446655440048', 'Beni Mellal', 'Safi', 327),
('550e8400-e29b-41d4-a716-446655440049', 'Beni Mellal', 'Agadir', 413),
('550e8400-e29b-41d4-a716-446655440050', 'Khouribga', 'Casablanca', 120);
