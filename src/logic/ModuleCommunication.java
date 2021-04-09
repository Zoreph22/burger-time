package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ModuleCommunication {
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;

    /**
     * Connecte le client au serveur
     * 
     * @throws IOException
     */
    public void connexion() throws IOException {
        // Création du socket entre client et serveur
        this.socket = new Socket("127.0.0.1", 1234);
    }

    /**
     * Crée les deux flux de communication
     * 
     * @throws IOException Erreur retournée en cas de problème de communication
     */
    public void creationFlux() throws IOException {
        // Création du gestionnaire de flux entrant
        InputStreamReader iSReader = new InputStreamReader(this.socket.getInputStream());
        this.fluxEntrant = new BufferedReader(iSReader);

        // Création du gestionnaire de flux sortant
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(), true);
    }

    /**
     * Envoie un message au serveur
     * 
     * @param message le message à envoyer
     */
    public void envoyerMessage(String message) {
        System.out.println(">>" + message);
        this.fluxSortant.println(message);
    }

    /**
     * Récupère un message en provenance du serveur
     * 
     * @return le message récupéré
     * @throws IOException Erreur renvoyée en cas de problème de communication
     */
    public String recevoirMessage() throws IOException {
        String message = this.fluxEntrant.readLine();
        System.out.println("<<" + message);
        return message;
    }
}