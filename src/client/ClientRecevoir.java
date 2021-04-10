package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui gère les actions en fonction des messages envoyés et reçus au
 * serveur
 */
public class ClientRecevoir extends Thread {

    // Socket du serveur
    private ClientSocket socket;
    // Message reçu du serveur
    private String msgRecu;
    // La communication avec le serveur est active ?
    private boolean enCommunication;

    /**
     * @param socket Socket du serveur
     */
    public ClientRecevoir(ClientSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        this.enCommunication = true;
        this.boucleCommunication();
    }

    public void boucleCommunication() {
        while (this.enCommunication && !this.socket.isClosed()) {
            // Réception d'un message du serveur
            try {
                this.msgRecu = this.socket.recevoir();
            } catch (IOException ex) {
                Logger.getLogger(ClientRecevoir.class.getName()).log(Level.SEVERE, null, ex);
            }

            // On traite ce message
            this.reagirAuMessageRecu();
        }
    }

    public void reagirAuMessageRecu() {
        switch (this.msgRecu) {
            case "STOP_CONNECTION":
                this.enCommunication = false;
                this.socket.deconnecter();
                break;
            default:
                System.err.println("Message reçu inconnu : " + this.msgRecu + ".");
        }
    }
}
