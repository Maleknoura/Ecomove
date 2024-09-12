package Service;

import DAO.ClientDAO;
import Model.Client;

public class ClientService {
    private ClientDAO clientDAO;
    public ClientService(ClientDAO clientDAO){
        this.clientDAO=clientDAO;
    }
    public void createClient(Client client){
        if (clientDAO.emailExists(client.getEmail())) {
            System.out.println("Email already exists");
            return;
        }
        clientDAO.createClient(client);
    }
    public Client getClientByEmail(String email) {
        return clientDAO.getClientByEmail(email);
    }
    public void updateClient(String email, String newFirstName, String newLastName, String newPhoneNumber) {
        Client client = clientDAO.getClientByEmail(email);

        if (client != null) {
            client.setFirstName(newFirstName);
            client.setLastName(newLastName);
            client.setPhoneNumber(newPhoneNumber);

            clientDAO.updateClient(client);
            System.out.println("Client information updated successfully.");
        } else {
            System.out.println("Client with email " + email + " not found.");
        }
    }
}
