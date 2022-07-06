package messages.serveur;

import client.ClientSocket;
import messages.Message;

/**
 * Message indiquant la déconnexion au serveur
 */
public class MessageDeconnexion extends Message {

    /**
     * Arrêter la connexion socket avec le serveur
     */
    @Override
    public void action() {
        ClientSocket.getInstance().deconnecter();
    }

}
