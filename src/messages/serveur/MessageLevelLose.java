package messages.serveur;

import client.ClientSocket;
import messages.Message;

/**
 * Message indiquant si le niveau est perdu
 */
public class MessageLevelLose extends Message {

    /**
     * Afficher l'écran de fin de partie
     */
    @Override
    public void action() {
        ClientSocket.getInstance().getGame().perdreNiveau();
    }

}
