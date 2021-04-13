package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import logic.Player;
import messages.Message;
import serveur.phases.PhasePartie;

/**
 * Message informant de la mort d'un joueur
 */
public class MessagePlayerDied extends Message {

    // Identifiant du joueur
    private UUID idJoueur;

    /**
     * @param idJoueur Identifiant du joueur
     */
    public MessagePlayerDied(String idJoueur) {
        this.idJoueur = UUID.fromString(idJoueur);
    }

    /**
     * Despawner le joueur du niveau
     */
    @Override
    public void action() {
        ClientSocket socket = ClientSocket.getInstance();
        PhasePartie partie = (PhasePartie) socket.getGame().getPhaseCourante();
        Player player = partie.getLevel().getPlayers().getPlayer(this.idJoueur);
        player.getCellules()[player.getPosition().getPosi()][player.getPosition().getPosj()].setEntity(null);
        partie.getLevel().printAll();
    }

}
