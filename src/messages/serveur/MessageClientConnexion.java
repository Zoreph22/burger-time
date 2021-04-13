package messages.serveur;

import client.ClientSocket;
import java.util.UUID;
import messages.Message;
import serveur.phases.Phase;
import serveur.phases.PhaseLobby;
import utils.RawConsoleInput;

/**
 * Message indiquant qu'un joueur s'est connecté au serveur
 */
public class MessageClientConnexion extends Message {

    // Identifiant du client
    private UUID idClient;

    /**
     * @param idClient Identifiant du client
     */
    public MessageClientConnexion(String idClient) {
        this.idClient = UUID.fromString(idClient);
    }

    /**
     * Connecter le client du serveur
     */
    @Override
    public void action() {
        ClientSocket socket = ClientSocket.getInstance();
        Phase phase = socket.getGame().getPhaseCourante();

        socket.addClientId(this.idClient);
        
        // Si on est en phase lobby, enlever le être prêt
        if (phase.getNom().equals("PhaseLobby")) {
            PhaseLobby lobby = (PhaseLobby) phase;
            lobby.setEstPret(false);
            lobby.afficher();
        }
    }

}
