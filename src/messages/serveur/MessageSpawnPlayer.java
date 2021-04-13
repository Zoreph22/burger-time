package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import logic.Position;
import messages.Message;
import serveur.phases.PhaseLobby;
import serveur.phases.PhasePartie;

/**
 * Message informant de faire spawn un joueur sur le niveau
 */
public class MessageSpawnPlayer extends Message {

    // Identifiant du client
    private UUID idClient;
    // Position de spawn
    private Position pos;
    // Symbole du joueur
    private String symbole;

    /**
     * @param idClient Identifiant du client
     * @param posi Position i
     * @param posj Position j
     * @param symbole Symbole du joueur
     */
    public MessageSpawnPlayer(String idClient, String posi, String posj, String symbole) {
        this.idClient = UUID.fromString(idClient);
        this.pos = new Position(Integer.valueOf(posi), Integer.valueOf(posj));
        this.symbole = symbole;
    }

    /**
     * Spawner tous les joueurs
     */
    @Override
    public void action() {
        ClientSocket socket = ClientSocket.getInstance();
        PhasePartie partie = (PhasePartie) socket.getGame().getPhaseCourante();

        // Si le spawn à faire est son joueur
        if (this.idClient.equals(socket.getIdClient())) {
            partie.getLevel().spawnMonJoueur(this.idClient, this.pos);
        } else {
            partie.getLevel().spawnOtherPlayer(this.idClient, this.pos, this.symbole);
        }
    }

}
