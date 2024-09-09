package Model;

import java.util.UUID;

public class ReservationTicket {

    private UUID id;
    private Reservation reservation;
    private Ticket ticket;

    public ReservationTicket(UUID id, Reservation reservation, Ticket ticket) {
        this.id = id;
        this.reservation = reservation;
        this.ticket = ticket;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
