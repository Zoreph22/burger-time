package serveur;

import utils.RawConsoleInput;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
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
    // Le serveur doit accepter les demandes de connexion ?
    private boolean isAccepteCon;
    // Socket du serveur
    private ServerSocket serverSocket;
    // Gestionnaire des clients connectés (clé = identifiant du client)
    private HashMap<UUID, ClientHandler> clients = new HashMap<>();
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
        RawConsoleInput.println("Démarrage du serveur...");
        RawConsoleInput.clear();

        try {
            this.serverSocket = new ServerSocket(25565);

            // Hook permettant d'éteindre le serveur à la fermeture du processus
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                this.stopServeur();
            }));

            RawConsoleInput.println("Serveur démarré.");
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
        this.isAccepteCon = false;
        RawConsoleInput.println("Fermeture du serveur...");

        for (ClientHandler client : this.clients.values()) {
            this.broadcast("SERVER_DISCONNECT");
            client.deconnecter();
        }

        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServeurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        RawConsoleInput.println("Serveur fermé.");
    }

    /**
     * Déconnecter un client du serveur
     *
     * @param idClient Identifiant client
     */
    public void deconnecterClient(UUID idClient) {
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
        this.isAccepteCon = true;
        this.game.setPhaseCourante(new PhaseLobby());
        RawConsoleInput.println("En attente de demandes de connexion...");

        // On écoute les demandes de connexion de la part des clients
        while (this.isAccepteCon) {
            // Établir la connexion avec le client
            Socket socket;

            try {
                socket = serverSocket.accept();

                if (this.isAccepteCon) {
                    ClientHandler handler = new ClientHandler(socket, UUID.randomUUID());
                    this.clients.put(handler.getClientId(), handler);
                    this.nbClients++;
                    handler.start();
                } else {
                    socket.close();
                }
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

    /**
     * Modifier si le serveur accepte les demandes de connexion
     *
     * @param accept Accepter les demandes de connexion ?
     */
    public void setAcceptCon(boolean accept) {
        this.isAccepteCon = accept;
    }

    /**
     * Retourner les identifiants des clients
     *
     * @return Identfiants clients
     */
    public Collection<UUID> getClientsId() {
        return this.clients.keySet();
    }

    /**
     * Retourner le nombre de clients connectés
     *
     * @return Nombre clients
     */
    public int getClientsNumber() {
        return this.clients.size();
    }
}
