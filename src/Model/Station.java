package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Station {

    private UUID id;
    private String departureLocation;
    private String arrivalLocation;
    private double distance;
    private List<Ticket> tickets;

    public Station(UUID id, String departureLocation, String arrivalLocation, double distance) {
        this.id = id;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.distance = distance;
        this.tickets = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setStation(this);
    }
}
