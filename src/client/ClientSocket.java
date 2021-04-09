package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe établissant la communication avec le serveur
 */
public class ClientSocket {

    /**
     * Socket du serveur
     */
    private Socket socket;

    /**
     * Flux de réception des messages
     */
    private BufferedReader fluxEntrant;

    /**
     * Flux de transmission des messages
     */
    private PrintWriter fluxSortant;

    /**
     * Connexion au serveur sur le port 25000
     *
     * @param ip IP du serveur
     * @throws IOException
     */
    public void connecter(String ip) throws IOException {
        this.socket = new Socket(ip, 25000);
        this.initFlux();
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
     * @param message le message à envoyer
     */
    public void envoyer(String message) {
        System.out.println(">> " + message);
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
        System.out.println("<< " + message);
        return message;
    }
}
