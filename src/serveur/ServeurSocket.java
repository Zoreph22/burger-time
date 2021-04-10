package serveur;

import client.RawConsoleInput;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe établissant la connexion avec les clients
 */
public class ServeurSocket extends Thread {

    // Le thread est en cours d'exécution ?
    private boolean running;
    // Socket du serveur
    private ServerSocket serverSocket;
    // Gestionnaire des clients connectés
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public ServeurSocket() {
        this.demarrer();
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

        for (ClientHandler client : this.clients) {
            this.broadcast("STOP_CONNECTION");
            client.deconnecter();
        }

        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServeurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Serveur fermé.");
    }

    @Override
    public void run() {
        this.running = true;
        this.ecouterConnexions();
    }

    /**
     * Écouter les demandes de connexion de la part des clients
     *
     * @throws IOException
     */
    public void ecouterConnexions() {
        System.out.println("En attente de demandes de connexion...");

        // On écoute les demandes de connexion de la part des clients
        while (this.running) {
            // Établir la connexion avec le client
            Socket socket;

            try {
                ClientHandler handler = new ClientHandler(serverSocket.accept());
                this.clients.add(handler);
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
        for (ClientHandler client : this.clients) {
            client.envoyer(msg);
        }
    }
}
