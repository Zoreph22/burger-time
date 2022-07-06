package messages.client;

import java.util.UUID;
import logic.Level;
import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.PhaseLobby;
import serveur.phases.PhasePartie;

/**
 * Message indiquant qu'un joueur s'est déplacé
 */
public class MessagePlayerMoved extends Message {

    // Identifiant du client
    private UUID idClient;
    // Direction de déplacement
    private String direction;

    /**
     * @param idClient Identifiant client
     * @param direction Direction UP, DOWN, LEFT, RIGHT
     */
    public MessagePlayerMoved(String idClient, String direction) {
        this.idClient = UUID.fromString(idClient);
        this.direction = direction;
    }

    /**
     * Déplacer le joueur et envoyer la réponse à tout le monde
     */
    @Override
    public void action() {
        PhasePartie partie = (PhasePartie) ServeurSocket.getInstance().getGame().getPhaseCourante();
        Level level = partie.getLevel();

        // Ne rien faire si le joueur est mort
        if (level.getPlayers().getPlayer(this.idClient).getMort()) {
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

        ServeurSocket.getInstance().broadcast("SERVER_PLAYER_MOVED|" + this.idClient + "|" + this.direction);
    }

}
