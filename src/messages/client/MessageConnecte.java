package messages.client;

import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant qu'un joueur s'est connecté au serveur
 */
public class MessageConnecte extends Message {

    // Identifiant du client
    private int idClient;

    /**
     * @param idClient Identifiant client
     */
    public MessageConnecte(String idClient) {

    }

    /**
     * Répondre en envoyant les informations du lobby
     */
    @Override
    public void action() {
        PhaseLobby lobby = (PhaseLobby) ServeurSocket.getInstance().getGame().getPhaseCourante();
        ServeurSocket.getInstance().broadcast("SERVER_LOBBY_REFRESH|" + lobby.getNbJoueurs() + "|" + lobby.getNbPrets());
    }

}
