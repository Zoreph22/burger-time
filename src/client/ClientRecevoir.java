package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui gère les actions en fonction des messages envoyés et reçus au
 * serveur
 */
public class ClientRecevoir extends Thread {

    private String msgRecu;
    private boolean enCommunication;

    /**
     * Socket du serveur
     */
    private ClientSocket socket;

    /**
     * @param socket Socket du serveur
     */
    public ClientRecevoir(ClientSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        this.boucleCommunication();
    }

    public void boucleCommunication() {
        this.enCommunication = true;

        while (this.enCommunication) {
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
        // en fonction du message, on exécute un code spécifique
    }
}
