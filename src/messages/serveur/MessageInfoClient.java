package messages.serveur;

import client.ClientSocket;
import messages.Message;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant les informations du client
 */
public class MessageInfoClient extends Message {

    // Identifiant du client
    private int idClient;

    /**
     * @param idClient Identifiant du client
     */
    public MessageInfoClient(String idClient) {
        this.idClient = Integer.valueOf(idClient);
    }

    /**
     * Stocker les informations du client
     */
    @Override
    public void action() {
        ClientSocket.getInstance().setIdClient(this.idClient);
        ClientSocket.getInstance().getGame().setPhaseCourante(new PhaseLobby()); // Phase lobby
        ClientSocket.getInstance().envoyer("CLIENT_CONNECTED|" + this.idClient);
    }
    
}
