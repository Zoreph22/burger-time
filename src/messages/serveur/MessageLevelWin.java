package messages.serveur;

import client.ClientSocket;
import messages.Message;

/**
 * Message indiquant si le niveau est gagné
 */
public class MessageLevelWin extends Message {

    /**
     * Stocker les informations du client
     */
    @Override
    public void action() {
        ClientSocket.getInstance().getGame().gagnerNiveau();
    }

}
