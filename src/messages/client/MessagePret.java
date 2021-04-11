package messages.client;

import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.PhaseLobby;
import serveur.phases.PhasePartie;

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

        if (lobby.getNbJoueurs() == lobby.getNbPrets()) {
            this.demarrerPartie();
        }
    }

    /**
     * Démarrer la phase partie, si tous les joueurs sont prêts
     */
    private void demarrerPartie() {
        PhasePartie partie = new PhasePartie();
        partie.setLevel(0);
        ServeurSocket.getInstance().getGame().setPhaseCourante(partie);
        ServeurSocket.getInstance().broadcast("SERVER_LOAD_LEVEL|" + partie.getLevel());
    }

}
