EcoMove - Module de gestion des clients et des réservations
Description
Ce projet fait partie de la plateforme EcoMove, qui permet la gestion des billets de transport pour les clients souhaitant réserver des tickets de différents moyens de transport (avion, train, bus). Ce module en mode console permet d'enregistrer les informations des clients, de rechercher des billets disponibles, et de gérer les réservations sans authentification et sans gestion de paiement.

Fonctionnalités principales
1. Enregistrement des clients
   Les utilisateurs peuvent s'inscrire en fournissant les informations suivantes :
   Nom
   Prénom
   Adresse e-mail
   Numéro de téléphone
   Si le client est déjà inscrit, ses informations sont récupérées pour faciliter la réservation.
2. Recherche des clients
   Permet de rechercher un client existant à partir du nom ou de l'adresse e-mail avant de commencer une recherche ou une réservation.
3. Recherche et réservation de billets
   Les clients peuvent effectuer une recherche en entrant la ville de départ, la ville de destination, et la date de départ.
   Les billets disponibles sont affichés avec les informations du transporteur, les horaires, la durée et le prix.
   Les clients peuvent sélectionner et réserver un billet.
4. Gestion des réservations
   Les clients peuvent consulter leurs réservations actuelles, avec la possibilité d'annuler une réservation.
   Les informations de réservation comprennent les détails du trajet, les compagnies concernées, et le prix.
   Technologies utilisées
   Java (JDK 8+)
   PostgreSQL pour la base de données
   JDBC pour les interactions avec la base de données
   UML pour la modélisation du projet
   Lambda Expressions et Stream API pour la programmation fonctionnelle
   JPA pour la gestion des entités
   Modèle UML
   Le modèle UML du projet est disponible via ce lien :
   UML - Lucidchart

Jira Board
Le suivi des tâches, des user stories et des sprints de développement est disponible sur Jira :
Jira - EcoMove

Installation
Cloner le projet depuis GitHub :

bash
Copier le code
git clone https://github.com/nom-utilisateur/ecomove-client-reservations.git
Importer le projet dans votre IDE préféré (IntelliJ IDEA recommandé).

Configurer la base de données PostgreSQL :

Créer une base de données ecomove_db.
Configurer le fichier application.properties avec vos informations de connexion à la base de données.
Lancer l'application en exécutant la classe principale Main.java.

Utilisation
Lancer l'application.
Suivre les instructions dans la console pour :
Inscrire un nouveau client
Rechercher des billets disponibles
Réserver un billet
Consulter ou annuler des réservations