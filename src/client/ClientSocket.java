package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import logic.Game;
import serveur.phases.PhaseLobby;
import utils.RawConsoleInput;

/**
 * Classe singleton établissant la connexion avec la serveur
 */
public class ClientSocket {

    // Instance unique de la classe
    private static ClientSocket instance;
    // Indique si le processus est en mode client
    private static boolean isClient = false;

    // Socket du serveur
    private Socket socket;
    // Flux de réception des messages
    private BufferedReader fluxEntrant;
    // Flux de transmission des messages
    private PrintWriter fluxSortant;
    // Instance du jeu
    private Game game;
    // Identifiant du client
    private UUID id;
    // Identifiant des autres clients connectés au serveur
    private ArrayList<UUID> clientsId = new ArrayList<>();

    private ClientSocket() {
        this.game = new Game();
    }

    /**
     * Retourner l'instance unique ClientSocket
     *
     * @return Instance unique
     */
    public static ClientSocket getInstance() {
        if (instance == null) {
            instance = new ClientSocket();
            isClient = true;
        }

        return instance;
    }

    /**
     * Retourner si le processus est en mode client
     *
     * @return Mode client ?
     */
    public static boolean isClient() {
        return isClient;
    }

    /**
     * Ajouter un identifiant d'un autre client du serveur
     *
     * @param idClient Identifiant autre client
     */
    public void addClientId(UUID idClient) {
        this.clientsId.add(idClient);
    }

    /**
     * Supprimer un identifiant d'un autre client du serveur
     *
     * @param idClient Identifiant autre client
     */
    public void removeClientId(UUID idClient) {
        this.clientsId.remove(idClient);
    }

    /**
     * Modifier son identifiant client
     *
     * @param idClient Identifiant client
     */
    public void setIdClient(UUID idClient) {
        this.id = idClient;
    }

    /**
     * Retourner son identifiant client
     *
     * @return Identifiant client
     */
    public UUID getIdClient() {
        return this.id;
    }

    /**
     * Connexion au serveur
     *
     * @param ip IP du serveur
     * @throws IOException
     */
    public void connecter(String ip) throws IOException {
        this.socket = new Socket(ip, 25565);
        this.initFlux();

        // Hook permettant de se déconnecter du serveur à la fin du processus
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.deconnecter();
        }));
    }

    /**
     * Déconnexion du serveur
     */
    public void deconnecter() {
        RawConsoleInput.println("Déconnexion du serveur...");

        this.envoyer("CLIENT_DISCONNECTED|" + this.id);

        try {
            this.socket.close();
        } catch (IOException ex) {
            System.err.println("Erreur de déconnexion du serveur : " + ex.getMessage() + ".");
        }

        RawConsoleInput.println("Déconnecté du serveur.");
        RawConsoleInput.println("Appuyez sur une touche pour quitter le jeu.");
    }

    /**
     * Le socket est-il fermé ?
     *
     * @return true si socket fermé
     */
    public boolean isClosed() {
        return this.socket.isClosed();
    }

    /**
     * Créer les deux flux de communication
     *
     * @throws IOException Erreur retournée en cas de problème de communication
     */
    public void initFlux() throws IOException {
        // Création du gestionnaire de flux entrant
        this.fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        // Création du gestionnaire de flux sortant
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(), true);
    }

    /**
     * Envoyer un message au serveur
     *
     * @param message Le message à envoyer
     */
    public void envoyer(String message) {
        //RawConsoleInput.println(">> " + message);
        this.fluxSortant.println(message);
    }

    /**
     * Récupèrer un message en provenance du serveur
     *
     * @return Message récupéré
     * @throws IOException Erreur renvoyée en cas de problème de communication
     */
    public String recevoir() throws IOException {
        String message = this.fluxEntrant.readLine();
        //RawConsoleInput.println("<< " + message);
        return message;
    }

    public Game getGame() {
        return this.game;
    }
}
