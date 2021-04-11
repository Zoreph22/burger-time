package messages.client;

import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant qu'un joueur est prêt pour lancer la partie dans le lobby
 */
public class MessagePret extends Message {

    /**
     * Incrémenter le nombre de joueur prêt
     */
    @Override
    public void action() {
        PhaseLobby lobby = (PhaseLobby) ServeurSocket.getInstance().getGame().getPhaseCourante();
        lobby.incrementerNbPrets();
        ServeurSocket.getInstance().broadcast("SERVER_LOBBY_REFRESH|" + lobby.getNbJoueurs() + "|" + lobby.getNbPrets());
    }

}
