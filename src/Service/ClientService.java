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
    public void getClientByEmail(String email){
        clientDAO.getClientByEmail(email);
    }
}
