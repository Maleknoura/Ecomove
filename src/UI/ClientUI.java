package UI;

import DAO.ClientDAO;
import DAO.ContractDAO;
import Helpers.DijkstraAlgorithm;
import Helpers.Graph;
import DataBase.DbFunction;
import Model.Client;
import Service.ClientService;

import java.util.List;
import java.util.Scanner;

import java.util.Scanner;

public class ClientUI {

    private ClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientUI(ClientService clientService) {
        this.clientService = clientService;
    }
    public void start() {
        System.out.println("Bienvenue! Avez-vous déjà un compte ? (oui/non)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("oui")) {
            handleExistingClient();
        } else if (response.equals("non")) {
            handleNewClient();
        } else {
            System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
        }
    }

    private void handleExistingClient() {
        System.out.print("Veuillez saisir votre email : ");
        String email = scanner.nextLine().trim();

        Client client = clientService.getClientByEmail(email);
        if (client != null) {
            showClientMenu(client);
        } else {
            System.out.println("Aucun compte trouvé avec cet email. Voulez-vous créer un compte ? (oui/non)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("oui")) {
                handleNewClient();
            } else {
                System.out.println("Au revoir !");
            }
        }
    }

    private void handleNewClient() {
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine().trim();

        System.out.print("Entrez votre prénom : ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Entrez votre nom : ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Entrez votre numéro de téléphone : ");
        String phoneNumber = scanner.nextLine().trim();

        Client newClient = new Client(email, firstName, lastName, phoneNumber);
        clientService.createClient(newClient);

        System.out.println("Compte créé avec succès ! Vous pouvez maintenant accéder à votre menu.");
        showClientMenu(newClient);
    }

    private void showClientMenu(Client client) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu Client");
            System.out.println("1. Visiter mon historique");
            System.out.println("2. Mettre à jour mon profil");
            System.out.println("3. Rechercher sur les tickets");
            System.out.println("4. Quitter");

            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewHistory(client);
                    break;
                case 2:
                    updateProfile(client);
                    break;
                case 3:
                    searchTickets();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
                    break;
            }
        }
    }

    private void viewHistory(Client client) {
        System.out.println("Affichage de l'historique pour " + client.getFirstName() + " " + client.getLastName());
    }

    private void updateProfile(Client client) {
        System.out.println("Mise à jour du profil pour " + client.getFirstName() + " " + client.getLastName());
    }

    private void searchTickets() {
        System.out.println("Recherche de tickets...");
    }


}


