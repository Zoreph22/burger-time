package messages.client;

import java.util.UUID;
import messages.Message;
import serveur.ServeurSocket;
import serveur.phases.Phase;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant que le joueur s'est déconnecté du serveur
 */
public class MessageDeconnexionClient extends Message {

    // Identifiant du client
    private UUID idClient;

    /**
     * @param idClient Identifiant du client
     */
    public MessageDeconnexionClient(String idClient) {
        this.idClient = UUID.fromString(idClient);
    }

    /**
     * Déconnecter le client du serveur
     */
    @Override
    public void action() {
        ServeurSocket socket = ServeurSocket.getInstance();
        Phase phase = socket.getGame().getPhaseCourante();

        // Si on est en phase lobby, actualiser le nombre de joueurs
        if (phase.getNom().equals("PhaseLobby")) {
            PhaseLobby lobby = (PhaseLobby) phase;
            lobby.decrementerNbJoueurs();
            lobby.setNbPrets(0);
            socket.broadcast("SERVER_LOBBY_REFRESH|" + lobby.getNbJoueurs() + "|" + lobby.getNbPrets());
        // Sinon, fermer le serveur car un joueur a quitté en cours de partie
        } else {
            socket.stopServeur();
        }
        
        socket.deconnecterClient(this.idClient);
        socket.broadcast("SERVER_CLIENT_DISCONNECTED|" + this.idClient);
    }
    
}
