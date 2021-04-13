package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import logic.Position;
import messages.Message;
import serveur.phases.PhasePartie;

/**
 * Message informant de faire spawn un ennemi sur le niveau
 */
public class MessageSpawnEnemy extends Message {

    // Identifiant de l'ennemi
    private UUID idEntite;
    // Position de spawn
    private Position pos;

    /**
     * @param idEntite Identifiant de l'entité
     * @param posi Position i
     * @param posj Position j
     */
    public MessageSpawnEnemy(String idEntite, String posi, String posj) {
        this.idEntite = UUID.fromString(idEntite);
        this.pos = new Position(Integer.valueOf(posi), Integer.valueOf(posj));
    }

    /**
     * Spawner l'ennemi
     */
    @Override
    public void action() {
        ClientSocket socket = ClientSocket.getInstance();
        PhasePartie partie = (PhasePartie) socket.getGame().getPhaseCourante();
        partie.getLevel().spawnEnemyAt(this.idEntite, this.pos);
    }

}
