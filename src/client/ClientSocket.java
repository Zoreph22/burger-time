package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe établissant la connexion avec la serveur
 */
public class ClientSocket {

    // Socket du serveur
    private Socket socket;
    // Flux de réception des messages
    private BufferedReader fluxEntrant;
    // Flux de transmission des messages
    private PrintWriter fluxSortant;

    /**
     * Connexion au serveur
     *
     * @param ip IP du serveur
     * @throws IOException
     */
    public void connecter(String ip) throws IOException {
        this.socket = new Socket(ip, 25565);
        this.initFlux();
    }

    /**
     * Déconnexion du serveur
     */
    public void deconnecter() {
        System.out.println("Déconnexion du serveur...");

        try {
            this.socket.close();
        } catch (IOException ex) {
            System.err.println("Erreur de déconnexion du serveur : " + ex.getMessage() + ".");
        }

        System.out.println("Déconnecté du serveur.");
        System.out.println("Appuyez sur une touche pour quitter le jeu.");
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
