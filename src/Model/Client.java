package Model;

import java.util.List;

public class Client {

    private String Email;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private List<Reservation> reservations;

    public Client(String email,String firstName,String lastName,String phoneNumber){
        this.Email=email;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.PhoneNumber=phoneNumber;
    }
    public String getEmail(){
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getFirstName(){
        return FirstName;
    }
    public void setFirstName(String firstName){
        this.FirstName=firstName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public List <Reservation> getReservations(){
        return reservations;
    }
    public void addreservation(Reservation reservation){
        this.reservations.add(reservation);
    }

}
