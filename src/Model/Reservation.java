package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Reservation {

    private UUID id;
    private Date reservationDate;
    private boolean cancelled;
    private Client client;
    private List<ReservationTicket> reservationTickets;

    public Reservation(UUID id, Date reservationDate, boolean cancelled, Client client) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.cancelled = cancelled;
        this.client = client;
        this.reservationTickets = new ArrayList<>();
    }

    public Reservation(UUID id, boolean cancelled, Client client) {
        this.id = id;
        this.reservationDate = new Date();
        this.cancelled = cancelled;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public List<ReservationTicket> getReservationTickets() {
        return reservationTickets;
    }

    public void addReservationTicket(ReservationTicket reservationTicket) {
        this.reservationTickets.add(reservationTicket);
    }
}
