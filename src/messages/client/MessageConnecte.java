package messages.client;

import java.util.UUID;
import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant qu'un joueur s'est connecté au serveur
 */
public class MessageConnecte extends Message {

    // Identifiant du client
    private UUID idClient;

    /**
     * @param idClient Identifiant client
     */
    public MessageConnecte(String idClient) {
        this.idClient = UUID.fromString(idClient);
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
