package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import logic.Level;
import messages.Message;
import serveur.phases.Phase;
import serveur.phases.PhasePartie;

/**
 * Message indiquant qu'un joueur s'est déplacé
 */
public class MessageServerPlayerMoved extends Message {

    // Identifiant du client
    private UUID idClient;
    // Direction de déplacement
    private String direction;

    /**
     * @param idClient Identifiant client
     * @param direction Direction UP, DOWN, LEFT, RIGHT
     */
    public MessageServerPlayerMoved(String idClient, String direction) {
        this.idClient = UUID.fromString(idClient);
        this.direction = direction;
    }

    /**
     * Déplacer le joueur et envoyer la réponse à tout le monde
     */
    @Override
    public void action() {
        Phase phase = ClientSocket.getInstance().getGame().getPhaseCourante();

        // Ne rien faire, si on est pas en partie
        if (!phase.getNom().equals("PhasePartie")) {
            return;
        }

        PhasePartie partie = (PhasePartie) phase;
        Level level = partie.getLevel();

        // Ne rien faire, si on est mort
        if (level.getPlayers().getMonJoueur().getMort()) {
            return;
        }

        switch (this.direction) {
            case "UP":
                level.getPlayers().getPlayer(this.idClient).up(level.getCellules());
                break;
            case "DOWN":
                level.getPlayers().getPlayer(this.idClient).down(level.getCellules());
                break;
            case "LEFT":
                level.getPlayers().getPlayer(this.idClient).left(level.getCellules());
                break;
            case "RIGHT":
                level.getPlayers().getPlayer(this.idClient).right(level.getCellules());
                break;
        }

        level.printAll();
    }

}
