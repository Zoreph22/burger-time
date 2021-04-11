package serveur;

import utils.RawConsoleInput;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Game;
import serveur.phases.PhaseLobby;

/**
 * Classe singleton établissant la connexion avec les clients
 */
public class ServeurSocket extends Thread {

    // Instance unique de la classe
    private static ServeurSocket instance;

    // Le thread est en cours d'exécution ?
    private boolean running;
    // Socket du serveur
    private ServerSocket serverSocket;
    // Gestionnaire des clients connectés (clé = identifiant du client)
    private HashMap<Integer, ClientHandler> clients = new HashMap<>();
    // Nombre de clients connectés
    private int nbClients = 0;
    // Instance du jeu
    private Game game;

    private ServeurSocket() {
        this.game = new Game();
        this.demarrer();
    }

    /**
     * Retourner l'instance unique ServeurSocket
     *
     * @return Instance unique
     */
    public static ServeurSocket getInstance() {
        if (instance == null) {
            instance = new ServeurSocket();
        }

        return instance;
    }

    /**
     * Démarrer le serveur
     */
    private void demarrer() {
        System.out.println("Démarrage du serveur...");
        RawConsoleInput.clear();

        try {
            this.serverSocket = new ServerSocket(25565);

            // Hook permettant d'éteindre le serveur à la fermeture du processus
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                this.stopServeur();
            }));

            System.out.println("Serveur démarré.");
        } catch (IOException ex) {
            System.err.println("Erreur de démarrage du serveur : " + ex.getMessage() + ".");
        }
    }

    /**
     * Fermer les sockets
     *
     * @throws IOException
     */
    public void stopServeur() {
        this.running = false;
        System.out.println("Fermeture du serveur...");

        for (ClientHandler client : this.clients.values()) {
            this.broadcast("SERVER_DISCONNECT");
            client.deconnecter();
        }

        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServeurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Serveur fermé.");
    }

    /**
     * Déconnecter un client du serveur
     *
     * @param idClient Identifiant client
     */
    public void deconnecterClient(int idClient) {
        ClientHandler client = this.clients.get(idClient);
        client.deconnecter();
        this.clients.remove(idClient);
    }

    @Override
    public void run() {
        this.running = true;
        this.ecouterConnexions(); // Phase Lobby
    }

    /**
     * Écouter les demandes de connexion de la part des clients (Phase Lobby)
     *
     * @throws IOException
     */
    public void ecouterConnexions() {
        this.game.setPhaseCourante(new PhaseLobby());
        System.out.println("En attente de demandes de connexion...");

        // On écoute les demandes de connexion de la part des clients
        while (this.running) {
            // Établir la connexion avec le client
            Socket socket;

            try {
                ClientHandler handler = new ClientHandler(serverSocket.accept(), this.nbClients + 1);
                this.clients.put(handler.getClientId(), handler);
                this.nbClients++;
                handler.start();
            } catch (SocketException ex) {
            } catch (IOException ex) {
                System.err.println("Erreur de connexion avec un client : " + ex.getMessage() + ".");
                ex.printStackTrace(System.err);
            }
        }
    }

    /**
     * Envoyer un message à tous les clients
     *
     * @param msg Message à envoyer
     */
    public void broadcast(String msg) {
        for (ClientHandler client : this.clients.values()) {
            client.envoyer(msg);
        }
    }

    public Game getGame() {
        return this.game;
    }
}
