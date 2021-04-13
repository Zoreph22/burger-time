package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import logic.Level;
import messages.Message;
import serveur.phases.Phase;
import serveur.phases.PhasePartie;

/**
 * Message indiquant qu'un ennemi s'est déplacé
 */
public class MessageEnemyMoved extends Message {

    // Identifiant du client
    private UUID idEnnemi;
    // Direction de déplacement
    private String direction;

    /**
     * @param idEnnemi Identifiant ennemi
     * @param direction Direction UP, DOWN, LEFT, RIGHT
     */
    public MessageEnemyMoved(String idEnnemi, String direction) {
        this.idEnnemi = UUID.fromString(idEnnemi);
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

        switch (this.direction) {
            case "UP":
                level.getEnemys().getEnemy(this.idEnnemi).up(level.getCellules());
                break;
            case "DOWN":
                level.getEnemys().getEnemy(this.idEnnemi).down(level.getCellules());
                break;
            case "LEFT":
                level.getEnemys().getEnemy(this.idEnnemi).left(level.getCellules());
                break;
            case "RIGHT":
                level.getEnemys().getEnemy(this.idEnnemi).right(level.getCellules());
                break;
        }

        level.printAll();
    }

}
