package Model;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

public class Ticket {

        private UUID id;
        private TransportType transportType;
        private BigDecimal purchasePrice;
        private BigDecimal salePrice;
        private Date saleDate;
        private TicketStatus ticketStatus;
        private UUID contractId;
        private List<ReservationTicket> reservationTickets;
    private Trip trip;

        public Ticket(UUID id, TransportType transportType, BigDecimal purchasePrice,
                      BigDecimal salePrice, Date saleDate,
                      TicketStatus ticketStatus, UUID contractId) {
            this.id = id;
            this.transportType = transportType;
            this.purchasePrice = purchasePrice;
            this.salePrice = salePrice;
            this.saleDate = saleDate;
            this.ticketStatus = ticketStatus;
            this.contractId = contractId;
            this.reservationTickets = new ArrayList<>();
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public UUID getContractId() {
        return contractId;
    }

    public void setContractId(UUID contractId) {
        this.contractId = contractId;
    }
    public List<ReservationTicket> getReservationTickets() {
        return reservationTickets;
    }

    public void addReservationTicket(ReservationTicket reservationTicket) {
        this.reservationTickets.add(reservationTicket);
    }
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
