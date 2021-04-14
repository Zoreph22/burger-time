package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Message;
import messages.MessageFactory;

/**
 * Classe qui gère les actions en fonction des messages envoyés et reçus au
 * serveur
 */
public class ClientRecevoir extends Thread {

    // Socket du serveur
    private ClientSocket socket;
    // Message reçu du serveur
    private String msgRecu;

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
        while (!this.socket.isClosed()) {
            // Réception d'un message du serveur
            try {
                this.msgRecu = this.socket.recevoir();

                // On traite ce message
                this.reagirAuMessageRecu();
            } catch (IOException ex) {
                this.socket.deconnecter();
            }
        }
    }

    public void reagirAuMessageRecu() {
        Message msg;

        try {
            msg = MessageFactory.creerMessageServeur(this.msgRecu);
            msg.action();
        } catch (Exception ex) {
            //ex.printStackTrace(System.err);
        }
    }
}
