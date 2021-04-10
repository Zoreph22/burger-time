package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe qui gère la communication avec un client connecté
 */
public class ClientHandler extends Thread {

    // Le thread est en cours d'exécution ?
    private boolean running;
    // Socket du client
    private Socket clientSocket;
    // Flux de réception des messages
    private BufferedReader fluxEntrant;
    // Flux de transmission des messages
    private PrintWriter fluxSortant;
    // Message reçu
    private String msgRecu;

    /**
     * @param socket Socket du client connecté
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.connecter();
    }

    /**
     * Initialiser la connexion avec le client
     */
    public void connecter() {
        try {
            this.initFlux();
            this.log("Connexion effectuée");
        } catch (IOException ex) {
            this.error("Impossible d'initialiser les flux de communication : " + ex.getMessage());
        }
    }

    /**
     * Fermer avec la connexion avec le client
     */
    public void deconnecter() {
        this.running = false;
        this.log("Fermeture de la connexion");

        try {
            this.fluxEntrant.close();
            this.fluxSortant.close();
            this.clientSocket.close();
        } catch (IOException ex) {
            this.error("Erreur de fermeture de connexion : " + ex.getMessage());
        }
    }

    /**
     * Créer les deux flux de communication
     *
     * @throws IOException Erreur retournée en cas de problème de communication
     */
    public void initFlux() throws IOException {
        // Création du gestionnaire de flux entrant
        this.fluxEntrant = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        // Création du gestionnaire de flux sortant
        this.fluxSortant = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    /**
     * Logger un message dans la console serveur
     *
     * @param msg Message log
     */
    public void log(String msg) {
        System.out.println("[IP : " + this.clientSocket.getInetAddress() + "] " + msg + ".");
    }

    /**
     * Logger un message d'erreur dans la console serveur
     *
     * @param msg Message erreur
     */
    public void error(String msg) {
        System.err.println("[IP : " + this.clientSocket.getInetAddress() + "] " + msg + ".");
    }

    /**
     * Envoyer un message au serveur
     *
     * @param message Le message à envoyer
     */
    public void envoyer(String message) {
        this.log("[SEND] " + message);
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
        this.log("[RECV] " + message);
        return message;
    }

    @Override
    public void run() {
        this.running = true;
        this.boucleCommunication();
    }

    public void boucleCommunication() {
        while (this.running) {
            // Réception d'un message du client
            try {
                this.msgRecu = this.recevoir();
            } catch (IOException ex) {
                this.error("Erreur de réception : " + ex.getMessage());
            }

            // On traite ce message
            this.reagirAuMessageRecu();
        }
    }

    public void reagirAuMessageRecu() {
        // REAGIR
    }
}
